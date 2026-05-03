
package acme.features.any.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Inventor;

@Service
public class AnyInventorListService extends AbstractService<Any, Inventor> {

	//Internal state
	@Autowired
	private AnyInventorRepository	repository;
	@Autowired
	private AnyProjectRepository	projectRepository;
	private Collection<Inventor>	inventors;
	private Project					project;


	//AbstractService interface
	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("projectId", int.class);
		this.inventors = this.repository.findAllInventorsByProjectId(id);
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
		super.unbindObjects(this.inventors, "userAccount.username", "bio", "keyWords", "licensed");
	}
}
