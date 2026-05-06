
package acme.features.manager.tactic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.tactic.Tactic;
import acme.features.manager.strategy.ManagerStrategyRepository;
import acme.realms.Manager;

@Service
public class ManagerTacticListService extends AbstractService<Manager, Tactic> {

	@Autowired
	private ManagerTacticRepository		repository;

	@Autowired
	private ManagerStrategyRepository	strategyRepository;

	private Collection<Tactic>			tactics;
	private Strategy					strategy;


	@Override
	public void load() {
		int strategyId = super.getRequest().getData("strategyId", int.class);
		this.tactics = this.repository.findAllTacticByStrategyId(strategyId);
		this.strategy = this.strategyRepository.findStrategyById(strategyId);
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.strategy != null);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage");

	}
}
