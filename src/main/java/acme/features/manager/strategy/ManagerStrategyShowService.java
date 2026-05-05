
package acme.features.manager.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;

@Service
public class ManagerStrategyShowService extends AbstractService<Manager, Strategy> {

	@Autowired
	private ManagerStrategyRepository	repository;
	private Strategy					strategy;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		// Con que la estrategia exista, el Manager puede verla
		super.setAuthorised(this.strategy != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "expectedPercentage", "draftMode");
		super.unbindGlobal("strategyId", this.strategy.getId());
	}
}
