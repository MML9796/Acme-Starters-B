
package acme.features.manager.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Inventor;
import acme.realms.Manager;

@Service
public class ManagerInventorShowService extends AbstractService<Manager, Inventor> {

	@Autowired
	private ManagerInventorRepository	repository;

	private Inventor					inventor;


	@Override
	public void load() {
		if (super.getRequest().hasData("id")) {
			int id = super.getRequest().getData("id", int.class);
			this.inventor = this.repository.findOneInventorById(id);
		} else if (super.getRequest().hasData("inventionId")) {
			int inventionId = super.getRequest().getData("inventionId", int.class);
			this.inventor = this.repository.findOneInventorByInventionId(inventionId);
		}
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.inventor != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "userAccount.username", "bio", "keyWords", "licensed");

		if (super.getRequest().hasData("inventionId")) {
			int campaignId = super.getRequest().getData("inventionId", int.class);
			super.unbindGlobal("inventionId", campaignId);
		}
	}
}
