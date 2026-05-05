
package acme.features.manager.memberProject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.UserAccount;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.member.MemberProject;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Manager;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class ManagerMemberProjectDeleteService extends AbstractService<Manager, MemberProject> {

	@Autowired
	private ManagerMemberProjectRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepo;

	@Autowired
	private ManagerMemberRepository			memberRepo;

	private MemberProject					memberProject;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.memberProject = new MemberProject();
		Project project = this.projectRepo.findProjectById(projectId);
		this.memberProject.setProject(project);
	}

	@Override
	public void authorise() {
		boolean status;
		int pId = super.getRequest().getData("projectId", int.class);
		Project p = this.projectRepo.findProjectById(pId);
		int managerId = super.getRequest().getPrincipal().getAccountId();

		status = p != null && p.getManager().getUserAccount().getId() == managerId && p.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.memberProject);

		int roleSelectId = super.getRequest().getData("member", int.class);
		String role = super.getRequest().getData("role", String.class);
		UserAccount ua = null;
		if ("INVENTOR".equals(role))
			ua = this.memberRepo.findUserAccountByInventorId(roleSelectId);
		else if ("SPOKESPERSON".equals(role))
			ua = this.memberRepo.findUserAccountBySpokespersonId(roleSelectId);
		else if ("FUNDRAISER".equals(role))
			ua = this.memberRepo.findUserAccountByFundraiserId(roleSelectId);

		if (ua != null) {
			Member m = this.memberRepo.findOneByUserAccountId(ua.getId());
			if (m == null) {
				m = new Member();
				m.setUserAccount(ua);
				this.memberRepo.save(m);
			}
			this.memberProject.setMember(m);
		}

	}
	@Override
	public void validate() {
		super.validateObject(this.memberProject);
	}

	@Override
	public void execute() {
		if (this.memberProject != null && this.memberProject.getMember() != null) {
			int projectId = this.memberProject.getProject().getId();
			int userAccountId = this.memberProject.getMember().getUserAccount().getId();

			MemberProject realMp = this.repository.findOneByProjectIdAndUserAccountId(projectId, userAccountId);
			if (realMp != null)
				this.repository.delete(realMp);
		}
	}

	@Override
	public void unbind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		String role = super.getRequest().getData("role", String.class);
		SelectChoices choices = null;
		if ("INVENTOR".equals(role)) {
			Collection<Inventor> inv = this.memberRepo.findAssignedInventors(projectId);
			choices = SelectChoices.from(inv, "userAccount.username", null);

		} else if ("SPOKESPERSON".equals(role)) {
			Collection<Spokesperson> sp = this.memberRepo.findAssignedSpokespersons(projectId);
			choices = SelectChoices.from(sp, "userAccount.username", null);

		} else if ("FUNDRAISER".equals(role)) {
			Collection<Fundraiser> fd = this.memberRepo.findAssignedFundraisers(projectId);
			choices = SelectChoices.from(fd, "userAccount.username", null);

		}
		super.unbindObject(this.memberProject, "member");
		super.unbindGlobal("listaMiembros", choices);
		super.unbindGlobal("projectId", projectId);
		super.unbindGlobal("role", role);
	}
}
