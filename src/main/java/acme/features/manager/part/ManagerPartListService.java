
package acme.features.manager.part;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.part.Part;
import acme.features.manager.invention.ManagerInventionRepository; // Asegúrate de importar el tuyo
import acme.realms.Manager;

@Service
public class ManagerPartListService extends AbstractService<Manager, Part> {

	@Autowired
	private ManagerPartRepository		repository;

	@Autowired
	private ManagerInventionRepository	inventionRepository;

	private Collection<Part>			parts;
	private Invention					invention;


	@Override
	public void load() {
		int id = super.getRequest().getData("inventionId", int.class);
		this.parts = this.repository.findAllPartByInventionId(id);
		this.invention = this.inventionRepository.findInventionById(id);
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.invention != null);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "description", "cost", "kind");
	}
}
