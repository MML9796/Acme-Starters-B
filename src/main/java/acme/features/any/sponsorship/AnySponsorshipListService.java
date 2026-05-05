
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Sponsor;

@Service
public class AnySponsorshipListService extends AbstractService<Any, Sponsorship> {

	//Internal state
	@Autowired
	private AnySponsorshipRepository	repository;
	private Collection<Sponsorship>		sponsorship;
	private Project						project;
	@Autowired
	private AnyProjectRepository		projectRepository;


	//AbstractService interface
	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.sponsorship = this.repository.findAllSponsorshipByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.sponsorship = this.repository.findAllSponsorship();
	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId"))
			if (this.project == null || this.project.getDraftMode())
				status = false;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
		boolean isSponsor = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Sponsor.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isSponsor", isSponsor);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
