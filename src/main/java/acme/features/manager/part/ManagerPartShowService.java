
package acme.features.manager.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.part.Part;
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
		super.setAuthorised(this.part != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
		super.unbindGlobal("draftMode", this.part.getInvention().getDraftMode()); // Por si lo usas en el JSP
	}
}
