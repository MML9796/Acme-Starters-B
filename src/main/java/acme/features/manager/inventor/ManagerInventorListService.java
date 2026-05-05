
package acme.features.manager.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Inventor;
import acme.realms.Manager;

@Service
public class ManagerInventorListService extends AbstractService<Manager, Inventor> {

	@Autowired
	private ManagerInventorRepository	repository;

	@Autowired
	private ManagerProjectRepository	projectRepository;

	private Collection<Inventor>		inventors;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.inventors = this.repository.findAllInventor(projectId);
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
		super.unbindObjects(this.inventors, "userAccount.username", "bio", "keyWords", "licensed");
		super.unbindGlobal("projectId", super.getRequest().getData("projectId", int.class));
	}
}
