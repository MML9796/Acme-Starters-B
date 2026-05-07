
package acme.features.manager.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.tactic.Tactic;
import acme.entities.tactic.TacticKind;
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
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = this.tactic != null && this.tactic.getStrategy() != null && this.tactic.getStrategy().getProject() != null && this.tactic.getStrategy().getProject().getManager().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "expectedPercentage", "kind", "notes");
		super.unbindGlobal("draftMode", this.tactic.getStrategy().getDraftMode());
		super.unbindGlobal("id", this.tactic.getId());
		SelectChoices opcionesKind = SelectChoices.from(TacticKind.class, this.tactic.getKind());
		super.unbindGlobal("listaKinds", opcionesKind);
	}
}
