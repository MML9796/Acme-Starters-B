
package acme.features.manager.sponsor;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Manager;
import acme.realms.Sponsor;

@Controller
public class ManagerSponsorController extends AbstractController<Manager, Sponsor> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("show", ManagerSponsorShowService.class);
	}

}
