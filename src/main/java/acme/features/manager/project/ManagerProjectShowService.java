
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Manager;

@Service
public class ManagerProjectShowService extends AbstractService<Manager, Project> {

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
		boolean status = false;
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		if (this.project != null)
			status = this.project.getManager().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "effort", "draftMode");
		super.unbindGlobal("id", this.project.getId());
		super.unbindGlobal("draftMode", this.project.getDraftMode());
	}
}
