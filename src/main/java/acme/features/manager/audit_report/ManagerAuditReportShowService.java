
package acme.features.manager.audit_report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.realms.Manager;

@Service
public class ManagerAuditReportShowService extends AbstractService<Manager, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerAuditReportRepository	repository;

	private AuditReport						auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.auditReport != null && this.auditReport.getProject() != null && this.auditReport.getProject().getManager().getId() == managerId && !this.auditReport.getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "hours", "auditor");
	}
}
