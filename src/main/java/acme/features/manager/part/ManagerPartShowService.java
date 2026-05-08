
package acme.features.manager.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.part.Part;
import acme.entities.part.Part.PartKind;
import acme.realms.Manager;

@Service
public class ManagerPartShowService extends AbstractService<Manager, Part> {

	@Autowired
	private ManagerPartRepository	repository;

	private Part					part;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.part = this.repository.findPartById(id);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = this.part != null && this.part.getInvention() != null && this.part.getInvention().getProject() != null && this.part.getInvention().getProject().getManager().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
		super.unbindGlobal("draftMode", this.part.getInvention().getDraftMode());
		super.unbindGlobal("id", this.part.getId());
		SelectChoices opcionesKind = SelectChoices.from(PartKind.class, this.part.getKind());
		super.unbindGlobal("listaKinds", opcionesKind);
	}
}
