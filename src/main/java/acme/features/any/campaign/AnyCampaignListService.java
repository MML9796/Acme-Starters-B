
package acme.features.any.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;

@Service
public class AnyCampaignListService extends AbstractService<Any, Campaign> {

	//Internal state
	@Autowired
	private AnyCampaignRepository	repository;
	private Collection<Campaign>	campaign;
	private Project					project;
	@Autowired
	private AnyProjectRepository	projectRepository;


	//AbstractService interface
	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.campaign = this.repository.findAllCampaignByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.campaign = this.repository.findAllCampaign();
	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId"))
			if (this.project == null || this.project.getDraftMode())
				status = false;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaign, "ticker", "name", "startMoment", "endMoment", "monthsActive", "effort", "moreInfo");
	}
}
