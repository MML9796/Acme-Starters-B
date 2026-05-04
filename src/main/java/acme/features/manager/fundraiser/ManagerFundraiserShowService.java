
package acme.features.manager.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Manager;

@Service
public class ManagerFundraiserShowService extends AbstractService<Manager, Fundraiser> {

	@Autowired
	private ManagerFundraiserRepository	repository;

	@Autowired
	private ManagerProjectRepository	projectRepository;
	private Fundraiser					fundraiser;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.fundraiser = this.repository.findOneFundraiserById(id);
	}

	@Override
	public void authorise() {
		int projectId = super.getRequest().getData("projectId", int.class);
		Project p = this.projectRepository.findProjectById(projectId);
		int managerID = super.getRequest().getPrincipal().getActiveRealm().getId();
		boolean status = p.getManager().getId() == managerID && this.fundraiser != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.fundraiser, "userAccount.username", "bank", "statement", "agent");
	}
}
