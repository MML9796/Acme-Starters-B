
package acme.features.auditor.audit_report_assignment;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.AuditReportAssignment;
import acme.realms.Auditor;

@Controller
public class AuditReportAssignmentController extends AbstractController<Auditor, AuditReportAssignment> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("create", AuditReportAssignmentCreateService.class);
	}
}
