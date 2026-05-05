
package acme.features.manager.milestone;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.milestones.Milestone;
import acme.realms.Manager;

@Service
public class ManagerMilestoneListService extends AbstractService<Manager, Milestone> {

	@Autowired
	private ManagerMilestoneRepository	repository;

	private Collection<Milestone>		milestones;
	private Campaign					parent;


	@Override
	public void load() {
		int campaignId = super.getRequest().getData("campaignId", int.class);
		this.milestones = this.repository.findMilestonesByCampaignId(campaignId);
		this.parent = this.repository.findCampaignById(campaignId);
	}

	@Override
	public void authorise() {

		super.setAuthorised(this.parent != null);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "achievements", "effort", "kind");
	}
}
