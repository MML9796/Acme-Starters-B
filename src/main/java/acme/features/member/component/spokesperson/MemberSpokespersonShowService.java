
package acme.features.member.component.spokesperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class MemberSpokespersonShowService extends AbstractService<Member, Spokesperson> {

	@Autowired
	private MemberSpokespersonRepository	repository;

	private Spokesperson					spokesperson;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.spokesperson = this.repository.findOneSpokespersonById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;

		if (this.spokesperson != null)
			status = true;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.spokesperson, "userAccount.username", "cv", "achievements", "licensed");
	}
}
