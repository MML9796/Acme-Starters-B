
package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerInventionListService extends AbstractService<Manager, Invention> {

	@Autowired
	private ManagerInventionRepository	repository;

	private Collection<Invention>		inventions;
	@Autowired
	private ManagerProjectRepository	projectRepository;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.inventions = this.repository.findInventionsByProjectId(projectId);
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
		super.unbindObjects(this.inventions, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");

	}
}
