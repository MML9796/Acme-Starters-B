
package acme.features.member.component.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberFundraiserShowService extends AbstractService<Member, Fundraiser> {

	@Autowired
	private MemberFundraiserRepository	repository;

	@Autowired
	private MemberProjectRepository		projectRepository;
	private Fundraiser					fundraiser;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.fundraiser = this.repository.findOneFundraiserById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;

		if (this.fundraiser != null)
			status = true;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.fundraiser, "userAccount.username", "bank", "statement", "agent");
	}
}
