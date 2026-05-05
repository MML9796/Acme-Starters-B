
package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerStrategyListService extends AbstractService<Manager, Strategy> {

	@Autowired
	private ManagerStrategyRepository	repository;
	private Collection<Strategy>		strategies;

	@Autowired
	private ManagerProjectRepository	projectRepository;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.strategies = this.repository.findStrategyByProjectId(projectId);
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
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}
}
