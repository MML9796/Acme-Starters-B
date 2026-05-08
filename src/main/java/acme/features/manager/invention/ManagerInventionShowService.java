
package acme.features.manager.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.realms.Manager;

@Service
public class ManagerInventionShowService extends AbstractService<Manager, Invention> {

	@Autowired
	private ManagerInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		boolean status = this.invention != null && this.invention.getProject() != null && this.invention.getProject().getManager().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "monthsActive", "cost", "moreInfo", "draftMode");
		super.unbindGlobal("inventionId", this.invention.getId());
	}
}
