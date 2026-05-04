
package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;

@Service
public class ManagerStrategyListService extends AbstractService<Manager, Strategy> {

	@Autowired
	private ManagerStrategyRepository	repository;
	private Collection<Strategy>		strategies;


	@Override
	public void load() {
		// Cargamos todas las estrategias para el Manager
		this.strategies = this.repository.findAllStrategies();
	}

	@Override
	public void authorise() {
		// Acceso libre para el Manager a la lista general
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}
}
