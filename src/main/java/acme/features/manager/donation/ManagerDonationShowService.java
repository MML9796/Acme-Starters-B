
package acme.features.manager.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.donation.Donation;
import acme.realms.Manager;

@Service
public class ManagerDonationShowService extends AbstractService<Manager, Donation> {

	@Autowired
	private ManagerDonationRepository	repository;
	private Donation					donation;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.donation = this.repository.findDonationById(id);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		// Validamos toda la cadena desde Donation -> Sponsorship -> Project -> Manager
		boolean status = this.donation != null && this.donation.getSponsorship() != null && this.donation.getSponsorship().getProject() != null && this.donation.getSponsorship().getProject().getManager().getId() == managerId
			&& !this.donation.getSponsorship().getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.donation, "name", "notes", "money", "kind");
	}
}
