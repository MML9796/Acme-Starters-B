
package acme.features.sponsor.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Sponsor;

@Service
public class SponsorProjectListService extends AbstractService<Sponsor, Project> {

	//Internal state
	@Autowired
	private SponsorProjectRepository	repository;
	private Collection<Project>			project;


	//AbstractService interface
	@Override
	public void load() {
		int sponsorId;
		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.project = this.repository.findProjectsBySponsorId(sponsorId);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment");
	}
}
