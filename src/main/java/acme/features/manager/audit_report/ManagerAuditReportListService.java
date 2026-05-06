
package acme.features.manager.audit_report;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;
import acme.realms.Manager;

@Service
public class ManagerAuditReportListService extends AbstractService<Manager, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerAuditReportRepository	repository;

	private Collection<AuditReport>			auditReports;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int projectId;
		projectId = super.getRequest().getData("projectId", int.class);
		this.auditReports = this.repository.findAuditReportsByProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status;
		int managerId;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findProjectById(projectId);
		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = project != null && project.getManager().getId() == managerId && !project.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.auditReports, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "hours");
	}

}
