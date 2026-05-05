
package acme.features.manager.spokesperson;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;
import acme.realms.Spokesperson;

@Service
public class ManagerSpokespersonListService extends AbstractService<Manager, Spokesperson> {

	@Autowired
	private ManagerSpokespersonRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepository;

	private Collection<Spokesperson>		spokespersons;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.spokespersons = this.repository.findAllSpokesperson(projectId);
	}

	@Override
	public void authorise() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project p = this.projectRepository.findProjectById(projectId);

		int managerID = super.getRequest().getPrincipal().getAccountId();
		boolean status = p != null && p.getManager().getUserAccount().getId() == managerID;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.spokespersons, "userAccount.username", "cv", "achievements", "licensed");
		super.unbindGlobal("projectId", super.getRequest().getData("projectId", int.class));
	}
}
