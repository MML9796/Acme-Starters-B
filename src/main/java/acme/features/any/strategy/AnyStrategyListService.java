
package acme.features.any.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.any.project.AnyProjectRepository;

@Service
public class AnyStrategyListService extends AbstractService<Any, Strategy> {

	@Autowired
	private AnyStrategyRepository	repository;
	private Collection<Strategy>	strategies;
	@Autowired
	private AnyProjectRepository	projectRepository;
	private Project					project;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.strategies = this.repository.findAllStrategyByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.strategies = this.repository.findPublishedStrategies();
	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId"))
			if (this.project == null || this.project.getDraftMode())
				status = false;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}

}
