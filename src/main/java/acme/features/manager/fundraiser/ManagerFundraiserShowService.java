
package acme.features.manager.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Fundraiser;
import acme.realms.Manager;

@Service
public class ManagerFundraiserShowService extends AbstractService<Manager, Fundraiser> {

	@Autowired
	private ManagerFundraiserRepository	repository;

	private Fundraiser					fundraiser;


	@Override
	public void load() {
		if (super.getRequest().hasData("id")) {
			int id = super.getRequest().getData("id", int.class);
			this.fundraiser = this.repository.findOneFundraiserById(id);
		} else if (super.getRequest().hasData("strategyId")) {
			int strategyId = super.getRequest().getData("strategyId", int.class);
			this.fundraiser = this.repository.findOneFundraiserByStrategyId(strategyId);
		}
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.fundraiser != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.fundraiser, "userAccount.username", "bank", "statement", "agent");
		if (super.getRequest().hasData("strategyId")) {
			int strategyId = super.getRequest().getData("strategyId", int.class);
			super.unbindGlobal("strategyId", strategyId);
		}
	}
}
