
package acme.features.manager.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.milestones.Milestone;
import acme.entities.milestones.MilestoneKind;
import acme.realms.Manager;

@Service
public class ManagerMilestoneShowService extends AbstractService<Manager, Milestone> {

	@Autowired
	private ManagerMilestoneRepository	repository;

	private Milestone					milestone;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = this.milestone != null && this.milestone.getCampaign() != null && this.milestone.getCampaign().getProject() != null && this.milestone.getCampaign().getProject().getManager().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		super.unbindGlobal("id", this.milestone.getId());
		SelectChoices opcionesKind = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());
		super.unbindGlobal("listaKinds", opcionesKind);
	}
}
