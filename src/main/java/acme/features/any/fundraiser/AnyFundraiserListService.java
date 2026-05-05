
package acme.features.any.fundraiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Fundraiser;

@Service
public class AnyFundraiserListService extends AbstractService<Any, Fundraiser> {

	//Internal state
	@Autowired
	private AnyFundraiserRepository	repository;
	@Autowired
	private AnyProjectRepository	projectRepository;
	private Collection<Fundraiser>	fundraisers;
	private Project					project;


	//AbstractService interface
	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("projectId", int.class);
		this.fundraisers = this.repository.findAllFundraisersByProjectId(id);
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
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "agent", "statement");
	}
}
