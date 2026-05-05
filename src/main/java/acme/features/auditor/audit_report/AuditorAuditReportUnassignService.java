
package acme.features.auditor.audit_report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.realms.Auditor;

@Service
public class AuditorAuditReportUnassignService extends AbstractService<Auditor, AuditReport> {

	//Internal state
	@Autowired
	private AuditorAuditReportRepository	repository;
	private AuditReport						auditReport;


	//AbstractService interface
	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int auditorId, auditReportId;
		AuditReport aud;
		auditorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		auditReportId = super.getRequest().getData("id", int.class);
		aud = this.repository.findAuditReportById(auditReportId);
		status = aud != null && aud.getAuditor().getId() == auditorId && MomentHelper.getCurrentMoment().before(aud.getProjectUnassignMoment());
		;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.auditReport);
	}

	@Override
	public void validate() {
		super.validateObject(this.auditReport);
	}

	@Override
	public void execute() {
		this.auditReport.setProject(null);
		this.auditReport.setProjectUnassignMoment(null);
		this.repository.save(this.auditReport);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "hours", "auditor");

	}
}
