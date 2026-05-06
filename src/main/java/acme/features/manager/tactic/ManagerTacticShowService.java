
package acme.features.manager.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.tactic.Tactic;
import acme.realms.Manager;

@Service
public class ManagerTacticShowService extends AbstractService<Manager, Tactic> {

	@Autowired
	private ManagerTacticRepository	repository;
	private Tactic					tactic;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findTacticById(id);
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.tactic != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "expectedPercentage");
		super.unbindGlobal("draftMode", this.tactic.getStrategy().getDraftMode());
	}
}
