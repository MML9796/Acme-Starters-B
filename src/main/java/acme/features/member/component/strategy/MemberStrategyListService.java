
package acme.features.member.component.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberStrategyListService extends AbstractService<Member, Strategy> {

	@Autowired
	private MemberStrategyRepository	repository;
	@Autowired
	private MemberProjectRepository		projectRepository;
	private Collection<Strategy>		strategy;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.strategy = this.repository.findAllStrategyByProjectId(id);
		this.project = this.projectRepository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;

		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		if (this.project != null) {
			Integer count = this.projectRepository.checkProjectBelongsToMember(this.project.getId(), memberId);

			status = count != null && count > 0;
		}

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategy, "ticker", "name", "startMoment", "endMoment");
		boolean isFundraiser = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Fundraiser.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isFundraiser", isFundraiser);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}
}
