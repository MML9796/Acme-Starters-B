
package acme.features.manager.audit_section;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.audit_reports.AuditSection;
import acme.realms.Manager;

@Controller
public class ManagerAuditSectionController extends AbstractController<Manager, AuditSection> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerAuditSectionListService.class);
		super.addBasicCommand("show", ManagerAuditSectionShowService.class);
	}

}
