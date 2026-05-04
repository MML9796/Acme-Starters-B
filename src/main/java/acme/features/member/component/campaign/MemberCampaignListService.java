
package acme.features.member.component.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.projects.Project;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class MemberCampaignListService extends AbstractService<Member, Campaign> {

	@Autowired
	private MemberCampaignRepository	repository;
	@Autowired
	private MemberProjectRepository		projectRepository;
	private Collection<Campaign>		campaign;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.campaign = this.repository.findAllCampaignByProjectId(id);
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
		super.unbindObjects(this.campaign, "ticker", "name", "startMoment", "endMoment", "monthsActive", "effort", "moreInfo");
		boolean isSpokesperson = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Spokesperson.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isSpokesperson", isSpokesperson);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
