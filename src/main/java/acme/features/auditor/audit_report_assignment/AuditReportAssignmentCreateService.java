
package acme.features.auditor.audit_report_assignment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;
import acme.forms.AuditReportAssignment;
import acme.realms.Auditor;

@Service

public class AuditReportAssignmentCreateService extends AbstractService<Auditor, AuditReportAssignment> {

	@Autowired
	private AuditReportAssignmentRepository	repository;
	private AuditReportAssignment			auditReportAssigment;
	private Collection<AuditReport>			auditreports;
	private Project							project;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int auditorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.auditreports = this.repository.findAvailableAuditReportsByAuditorId(auditorId);
		this.auditReportAssigment = super.newObject(AuditReportAssignment.class);
		this.auditReportAssigment.setProjectId(projectId);

	}

	@Override
	public void authorise() {
		boolean status;
		status = this.project != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.auditReportAssigment, "auditReportId");
		this.auditReportAssigment.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.auditReportAssigment);

		boolean hasAuditReport = this.auditReportAssigment.getAuditReportId() != 0;
		super.state(hasAuditReport, "auditReportId", "auditor.audit-report-assignment.error.auditReportId.required");
	}

	@Override
	public void execute() {
		AuditReport auditreport = this.repository.findAuditReportById(this.auditReportAssigment.getAuditReportId());
		if (auditreport != null) {
			auditreport.setProject(this.project);
			this.repository.save(auditreport);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.auditreports, "ticker", null);
		super.unbindObject(this.auditReportAssigment, "auditReportId");
		super.unbindGlobal("listaAuditReports", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}
}
