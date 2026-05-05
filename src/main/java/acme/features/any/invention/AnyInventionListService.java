
package acme.features.any.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;

@Service
public class AnyInventionListService extends AbstractService<Any, Invention> {

	//Internal state
	@Autowired
	private AnyInventionRepository	repository;
	@Autowired
	private AnyProjectRepository	projectRepository;
	private Collection<Invention>	invention;
	private Project					project;


	//AbstractService interface
	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.invention = this.repository.findAllInventionByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.invention = this.repository.findAllInventionPublished();

	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId")) {
			if (this.project == null || this.project.getDraftMode())
				status=false;
		}
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}
}
