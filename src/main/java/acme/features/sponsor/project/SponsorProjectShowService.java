
package acme.features.sponsor.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Sponsor;

@Service
public class SponsorProjectShowService extends AbstractService<Sponsor, Project> {

	//Internal state
	@Autowired
	private SponsorProjectRepository	repository;
	private Project						project;


	//AbstractService interface
	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;
		if (this.project != null) {
			boolean isSponsored;
			int id;
			id = super.getRequest().getPrincipal().getActiveRealm().getId();
			isSponsored = this.repository.existsSponsorshipBySponsorIdAndProjectId(this.project.getId(), id);
			status = !this.project.getDraftMode() && isSponsored;
		}
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "effort");
		super.unbindGlobal("id", this.project.getId());
		super.unbindGlobal("managerId", this.project.getManager().getId());
	}
}
