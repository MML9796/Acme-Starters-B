
package acme.features.manager.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Manager;

@Service
public class ManagerCampaignShowService extends AbstractService<Manager, Campaign> {

	@Autowired
	private ManagerCampaignRepository	repository;
	private Campaign					campaign;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.campaign != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "monthsActive", "effort", "moreInfo");
		super.unbindGlobal("campaignId", this.campaign.getId());
	}
}
