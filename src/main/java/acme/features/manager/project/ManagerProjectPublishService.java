
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.fundraiser.strategy.FundraiserStrategyRepository;
import acme.features.inventor.invention.InventorInventionRepository;
import acme.features.spokesperson.campaign.SpokespersonCampaignRepository;
import acme.realms.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository		repository;
	private InventorInventionRepository		inventionRepository;
	private SpokespersonCampaignRepository	campaignRepository;
	private FundraiserStrategyRepository	strategyRepository;

	private Project							project;
	private Collection<Invention>			inventions;
	private Collection<Campaign>			campaigns;
	private Collection<Strategy>			strategies;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
		this.inventions = this.repository.findInventionsByProjectId(id);
		this.campaigns = this.repository.findCampaignsByProjectId(id);
		this.strategies = this.repository.findStrategiesByProjectId(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.project != null && this.project.getManager().isPrincipal() && this.project.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment");
	}

	@Override
	public void validate() {
		super.validateObject(this.project);
		{

			boolean atLeastOneInvention;
			int existingInventions;

			existingInventions = this.repository.findInventionsSizeById(this.project.getId());
			atLeastOneInvention = existingInventions >= 1;

			super.state(atLeastOneInvention, "*", "acme.validation.project.no-inventions.message");

		}
		this.inventions.forEach(invention -> {

			boolean atLeastOnePart;
			int existingParts;

			existingParts = this.inventionRepository.findPartsSizeById(invention.getId());
			atLeastOnePart = existingParts >= 1;

			super.state(atLeastOnePart, "*", "acme.validation.invention.existing-part.message");

		});
		this.campaigns.forEach(campaign -> {

			boolean atLeastOneMilestone;
			int existingMilestones;

			existingMilestones = this.campaignRepository.findMilestonesSizeById(campaign.getId());
			atLeastOneMilestone = existingMilestones >= 1;

			super.state(atLeastOneMilestone, "*", "acme.publish.campaign.noHaveMilestone.message");

		});
		this.strategies.forEach(strategy -> {

			boolean atLeastOneTactic;
			int existingTactics;

			existingTactics = this.strategyRepository.findTacticsSizeById(strategy.getId());
			atLeastOneTactic = existingTactics >= 1;

			super.state(atLeastOneTactic, "*", "acme.publish.strategy.noHaveTactic.message");

		});

	}

	@Override
	public void execute() {
		this.project.setDraftMode(false);
		this.inventions.forEach(i -> {
			i.setDraftMode(false);
			this.inventionRepository.save(i);
		});
		this.campaigns.forEach(i -> {
			i.setDraftMode(false);
			this.campaignRepository.save(i);
		});
		this.strategies.forEach(i -> {
			i.setDraftMode(false);
			this.strategyRepository.save(i);
		});
		this.repository.save(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOffMoment", "closeOutMoment", "draftMode", "effort", "manager");
	}
}
