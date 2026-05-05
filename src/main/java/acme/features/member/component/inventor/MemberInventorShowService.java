
package acme.features.member.component.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Inventor;
import acme.realms.Member;

@Service
public class MemberInventorShowService extends AbstractService<Member, Inventor> {

	@Autowired
	private MemberInventorRepository	repository;

	private Inventor					inventor;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.inventor = this.repository.findOneInventorById(id);
	}

	@Override
	public void authorise() {
		boolean status = false;

		if (this.inventor != null)
			status = true;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "userAccount.username", "bio", "keyWords", "licensed");
	}
}
