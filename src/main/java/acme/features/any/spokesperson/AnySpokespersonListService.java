
package acme.features.any.spokesperson;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Spokesperson;

@Service
public class AnySpokespersonListService extends AbstractService<Any, Spokesperson> {

	//Internal state
	@Autowired
	private AnySpokespersonRepository	repository;
	@Autowired
	private AnyProjectRepository		projectRepository;
	private Collection<Spokesperson>	spokespeople;
	private Project						project;


	//AbstractService interface
	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("projectId", int.class);
		this.spokespeople = this.repository.findAllSpokespeopleByProjectId(id);
		this.project = this.projectRepository.findProjectById(id);
	}
	@Override
	public void authorise() {
		boolean status = false;
		if (this.project != null)
			status = !this.project.getDraftMode();
		super.setAuthorised(status);

	}

	@Override
	public void unbind() {
		super.unbindObjects(this.spokespeople, "userAccount.username", "cv", "achievements", "licensed");
	}
}
