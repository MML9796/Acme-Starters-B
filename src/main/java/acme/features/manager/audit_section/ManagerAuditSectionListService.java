
package acme.features.manager.audit_section;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.entities.audit_reports.AuditSection;
import acme.realms.Manager;

@Service
public class ManagerAuditSectionListService extends AbstractService<Manager, AuditSection> {

	@Autowired
	private ManagerAuditSectionRepository	repository;

	private Collection<AuditSection>		auditSections;
	private AuditReport						parent;


	@Override
	public void load() {
		int auditReportId;

		auditReportId = super.getRequest().getData("auditReportId", int.class);
		this.auditSections = this.repository.findAuditSections(auditReportId);
		this.parent = this.repository.findAuditReportById(auditReportId);
	}

	@Override
	public void authorise() {
		boolean status;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.parent != null && this.parent.getProject() != null && this.parent.getProject().getManager().getId() == managerId && !this.parent.getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		int auditReportId;

		auditReportId = super.getRequest().getData("auditReportId", int.class);

		super.unbindObjects(this.auditSections, "name", "notes", "hours", "kind");
		super.unbindGlobal("auditReportId", auditReportId);
	}

}
