
package acme.features.member.component.spokesperson;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class MemberSpokespersonListService extends AbstractService<Member, Spokesperson> {

	@Autowired
	private MemberSpokespersonRepository	repository;

	@Autowired
	private MemberProjectRepository			projectRepository;

	private Collection<Spokesperson>		spokerpersons;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.spokerpersons = this.repository.findAllSpokesperson(projectId);
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
		super.unbindObjects(this.spokerpersons, "userAccount.username", "cv", "achievements", "licensed");
		super.unbindGlobal("projectId", super.getRequest().getData("projectId", int.class));
	}
}
