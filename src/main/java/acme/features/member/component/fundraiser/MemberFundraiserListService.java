
package acme.features.member.component.fundraiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberFundraiserListService extends AbstractService<Member, Fundraiser> {

	@Autowired
	private MemberFundraiserRepository	repository;

	@Autowired
	private MemberProjectRepository		projectRepository;

	private Collection<Fundraiser>		fundraisers;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.fundraisers = this.repository.findAllFundraiser(projectId);
	}

	@Override
	public void authorise() {
		boolean status = false;

		int projectId = super.getRequest().getData("projectId", int.class);

		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		Integer count = this.projectRepository.checkProjectBelongsToMember(projectId, memberId);

		status = count != null && count > 0;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "statement", "agent");
		super.unbindGlobal("projectId", super.getRequest().getData("projectId", int.class));
	}
}
