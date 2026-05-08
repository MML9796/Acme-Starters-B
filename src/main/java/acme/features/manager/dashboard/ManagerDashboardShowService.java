
package acme.features.manager.dashboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectRepository;
import acme.entities.strategy.Strategy;
import acme.forms.Dashboard;
import acme.realms.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerDashboardRepository	repository;
	@Autowired
	private ProjectRepository			projectRepository;

	private Dashboard					dashboard;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		Double numberOfProjects;
		Double deviationOfNumberOfProjectsWrtAvgOfOtherManagers;
		Double mimimumEffortOfProjects;
		Double maximumEffortOfProjects;
		Double averageEffortOfProjects;
		Double averageAbsoulteDeviationOfEffortOfProjects;
		int managerId;
		Collection<Project> projects;

		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		numberOfProjects = this.repository.numberOfProjects(managerId);
		deviationOfNumberOfProjectsWrtAvgOfOtherManagers = Math.abs(numberOfProjects - this.repository.averageOfProjectsOfOtherManagers(managerId));

		// Very inefficient, but needed since effort and monthsActive are transient
		projects = this.repository.findProjectsByManagerId(managerId);
		mimimumEffortOfProjects = null;
		maximumEffortOfProjects = .0;
		averageEffortOfProjects = .0;
		// Not possible to use super.newObject
		Map<Integer, Double> projectEfforts = new HashMap<>();
		for (Project p : projects) {
			Double projectEffort;

			Double totalMonths;
			List<Invention> inventions;
			List<Campaign> campaigns;
			List<Strategy> strategies;

			inventions = this.projectRepository.getProjectInventionsById(p.getId());
			campaigns = this.projectRepository.getProjectCampaignsById(p.getId());
			strategies = this.projectRepository.getProjectStrategiesById(p.getId());
			totalMonths = inventions.stream().mapToDouble(i -> i.getMonthsActive()).sum() + campaigns.stream().mapToDouble(c -> c.getMonthsActive()).sum() + strategies.stream().mapToDouble(s -> s.getMonthsActive()).sum();

			Integer involvedCount;
			Integer wrapper;

			wrapper = this.projectRepository.getProjectMemberCountById(p.getId());
			involvedCount = wrapper == null ? 0 : wrapper;

			projectEffort = involvedCount > 0 ? totalMonths / involvedCount : 0.0;
			projectEfforts.put(p.getId(), projectEffort);

			if (mimimumEffortOfProjects == null || mimimumEffortOfProjects > projectEffort)
				mimimumEffortOfProjects = projectEffort;
			if (maximumEffortOfProjects < projectEffort)
				maximumEffortOfProjects = projectEffort;
			averageEffortOfProjects += projectEffort / numberOfProjects;
		}
		if (mimimumEffortOfProjects == null)
			mimimumEffortOfProjects = .0;
		averageAbsoulteDeviationOfEffortOfProjects = .0;
		for (Project p : projects)
			averageAbsoulteDeviationOfEffortOfProjects += Math.abs(projectEfforts.get(p.getId()) - averageEffortOfProjects) / numberOfProjects;

		this.dashboard = super.newObject(Dashboard.class);
		this.dashboard.setNumberOfProjects(numberOfProjects);
		this.dashboard.setDeviationOfNumberOfProjectsWrtAvgOfOtherManagers(deviationOfNumberOfProjectsWrtAvgOfOtherManagers);
		this.dashboard.setMimimumEffortOfProjects(mimimumEffortOfProjects);
		this.dashboard.setMaximumEffortOfProjects(maximumEffortOfProjects);
		this.dashboard.setAverageEffortOfProjects(averageEffortOfProjects);
		this.dashboard.setAverageAbsoulteDeviationOfEffortOfProjects(averageAbsoulteDeviationOfEffortOfProjects);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.dashboard, //
			"numberOfProjects", "deviationOfNumberOfProjectsWrtAvgOfOtherManagers", //
			"mimimumEffortOfProjects", "maximumEffortOfProjects", "averageEffortOfProjects", "averageAbsoulteDeviationOfEffortOfProjects");
	}

}
