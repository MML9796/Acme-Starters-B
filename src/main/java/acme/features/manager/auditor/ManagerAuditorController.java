
package acme.features.manager.auditor;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Auditor;
import acme.realms.Manager;

@Controller
public class ManagerAuditorController extends AbstractController<Manager, Auditor> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("show", ManagerAuditorShowService.class);
	}

}
