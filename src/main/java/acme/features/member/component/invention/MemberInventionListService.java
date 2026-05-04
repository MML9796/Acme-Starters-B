
package acme.features.member.component.invention;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.projects.Project;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Inventor;
import acme.realms.Member;

@Service
public class MemberInventionListService extends AbstractService<Member, Invention> {

	@Autowired
	private MemberInventionRepository	repository;
	@Autowired
	private MemberProjectRepository		projectRepository;
	private Collection<Invention>		invention;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.invention = this.repository.findAllInventionByProjectId(id);
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
		super.unbindObjects(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
		super.unbindGlobal("projectId", this.project.getId());
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		boolean isInventor = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Inventor.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isInventor", isInventor);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}
}
