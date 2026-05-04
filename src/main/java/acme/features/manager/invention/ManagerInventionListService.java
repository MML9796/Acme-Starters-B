
package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.Manager;

@Service
public class ManagerInventionListService extends AbstractService<Manager, Invention> {

	@Autowired
	private ManagerInventionRepository	repository;

	private Collection<Invention>		inventions;


	@Override
	public void load() {
		this.inventions = this.repository.findAllInventions();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}
}
