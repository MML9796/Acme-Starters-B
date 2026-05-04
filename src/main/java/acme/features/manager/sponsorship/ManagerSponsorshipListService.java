
package acme.features.manager.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.realms.Manager;

@Service
public class ManagerSponsorshipListService extends AbstractService<Manager, Sponsorship> {

	@Autowired
	private ManagerSponsorshipRepository	repository;
	private Collection<Sponsorship>			sponsorships;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.sponsorships = this.repository.findSponsorshipsByProjectId(projectId);
	}

	@Override
	public void authorise() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.repository.findProjectById(projectId);
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = project != null && project.getManager().getId() == managerId && !project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}
}
