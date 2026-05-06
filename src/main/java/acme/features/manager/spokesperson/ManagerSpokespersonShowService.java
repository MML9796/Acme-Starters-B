
package acme.features.manager.spokesperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Manager;
import acme.realms.Spokesperson;

@Service
public class ManagerSpokespersonShowService extends AbstractService<Manager, Spokesperson> {

	@Autowired
	private ManagerSpokespersonRepository	repository;

	private Spokesperson					spokesperson;


	@Override
	public void load() {
		if (super.getRequest().hasData("id")) {
			int id = super.getRequest().getData("id", int.class);
			this.spokesperson = this.repository.findOneSpokespersonById(id);
		} else if (super.getRequest().hasData("campaignId")) {
			int campaignId = super.getRequest().getData("campaignId", int.class);
			this.spokesperson = this.repository.findOneSpokespersonByCampaignId(campaignId);
		}
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.spokesperson != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.spokesperson, "userAccount.username", "cv", "achievements", "licensed");

		if (super.getRequest().hasData("campaignId")) {
			int campaignId = super.getRequest().getData("campaignId", int.class);
			super.unbindGlobal("campaignId", campaignId);
		}
	}
}
