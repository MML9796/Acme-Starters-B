
package acme.features.manager.donation;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.donation.Donation;
import acme.realms.Manager;

@Controller
public class ManagerDonationController extends AbstractController<Manager, Donation> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", ManagerDonationListService.class);
		super.addBasicCommand("show", ManagerDonationShowService.class);
	}
}
