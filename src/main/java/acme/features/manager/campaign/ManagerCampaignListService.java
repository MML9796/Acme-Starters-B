
package acme.features.manager.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Manager;

@Service
public class ManagerCampaignListService extends AbstractService<Manager, Campaign> {

	@Autowired
	private ManagerCampaignRepository	repository;

	private Collection<Campaign>		campaigns;


	@Override
	public void load() {
		this.campaigns = this.repository.findAllCampaign();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "effort");
	}
}
