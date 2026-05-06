
package acme.features.manager.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.Manager;

@Service
public class ManagerSponsorshipShowService extends AbstractService<Manager, Sponsorship> {

	@Autowired
	private ManagerSponsorshipRepository	repository;
	private Sponsorship						sponsorship;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		// Comprobamos subiendo la jerarquía: Sponsorship -> Project -> Manager (y que esté publicado)
		boolean status = this.sponsorship != null && this.sponsorship.getProject() != null && this.sponsorship.getProject().getManager().getId() == managerId && !this.sponsorship.getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
	}
}
