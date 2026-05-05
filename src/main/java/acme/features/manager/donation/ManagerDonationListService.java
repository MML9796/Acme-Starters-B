
package acme.features.manager.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.donation.Donation;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.Manager;

@Service
public class ManagerDonationListService extends AbstractService<Manager, Donation> {

	@Autowired
	private ManagerDonationRepository	repository;
	private Collection<Donation>		donations;
	private Sponsorship					sponsorship;


	@Override
	public void load() {
		int sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.donations = this.repository.findAllDonationBySponsorshipId(sponsorshipId);
		this.sponsorship = this.repository.findSponsorshipById(sponsorshipId);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = this.sponsorship != null && this.sponsorship.getProject() != null && this.sponsorship.getProject().getManager().getId() == managerId && !this.sponsorship.getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");
	}
}
