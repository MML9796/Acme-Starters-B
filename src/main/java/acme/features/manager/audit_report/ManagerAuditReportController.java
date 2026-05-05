
package acme.features.manager.audit_report;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.audit_reports.AuditReport;
import acme.realms.Manager;

@Controller
public class ManagerAuditReportController extends AbstractController<Manager, AuditReport> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerAuditReportListService.class);
		super.addBasicCommand("show", ManagerAuditReportShowService.class);

	}

}
