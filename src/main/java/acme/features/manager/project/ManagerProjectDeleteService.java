
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.member.MemberProject;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	//Internal state
	@Autowired
	private ManagerProjectRepository	repository;
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
		boolean status;
		int managerId;
		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		status = this.project != null && this.project.getManager().getId() == managerId && this.project.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.project);
	}

	@Override
	public void validate() {
		super.validateObject(this.project);
	}

	@Override
	public void execute() {
		int id;
		id = super.getRequest().getData("id", int.class);

		Collection<Invention> inventions = this.repository.findInventionsByProjectId(id);
		for (Invention i : inventions) {
			i.setProject(null);
			this.repository.save(i);
		}

		Collection<Campaign> campaigns = this.repository.findCampaignsByProjectId(id);
		for (Campaign c : campaigns) {
			c.setProject(null);
			this.repository.save(c);
		}

		Collection<Strategy> strategies = this.repository.findStrategiesByProjectId(id);
		for (Strategy s : strategies) {
			s.setProject(null);
			this.repository.save(s);
		}

		Collection<MemberProject> memberProjects = this.repository.findMemberProjectsByProjectId(id);
		for (MemberProject m : memberProjects)
			this.repository.delete(m);

		this.repository.delete(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "draftMode");
	}

}
