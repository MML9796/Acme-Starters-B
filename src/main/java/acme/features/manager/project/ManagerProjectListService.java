
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Manager;

@Service
public class ManagerProjectListService extends AbstractService<Manager, Project> {

	//Internal state
	@Autowired
	private ManagerProjectRepository	repository;
	private Collection<Project>			project;


	//AbstractService interface
	@Override
	public void load() {
		int id;
		id = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.project = this.repository.findAllProjectByManagerId(id);
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
