
package acme.features.manager.fundraiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Manager;

@Service
public class ManagerFundraiserListService extends AbstractService<Manager, Fundraiser> {

	@Autowired
	private ManagerFundraiserRepository	repository;

	@Autowired
	private ManagerProjectRepository	projectRepository;

	private Collection<Fundraiser>		fundraisers;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.fundraisers = this.repository.findAllFundraiser(projectId);
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
		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.projectRepository.findProjectById(projectId);
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "statement", "agent");
		super.unbindGlobal("projectId", projectId);
		super.unbindGlobal("draftMode", project.getDraftMode());
	}
}
