
package acme.features.manager.memberProject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.member.MemberProject;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;
import acme.realms.Member;

@Service
public class ManagerMemberProjectCreateService extends AbstractService<Manager, MemberProject> {

	@Autowired
	private ManagerMemberProjectRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepo;

	@Autowired
	private ManagerMemberRepository			memberRepo;

	private MemberProject					memberProject;


	@Override
	public void load() {
		this.memberProject = new MemberProject();

		int projectId = super.getRequest().getData("projectId", int.class);
		Project project = this.projectRepo.findProjectById(projectId);
		this.memberProject.setProject(project);

		if (super.getRequest().hasData("member")) {
			int memberId = super.getRequest().getData("member", int.class);
			Member member = this.memberRepo.findOneById(memberId);
			this.memberProject.setMember(member);
		}
	}

	@Override
	public void authorise() {
		boolean status;
		int pId = super.getRequest().getData("projectId", int.class);
		Project p = this.projectRepo.findProjectById(pId);
		int managerId = super.getRequest().getPrincipal().getAccountId();

		status = p != null && p.getManager().getUserAccount().getId() == managerId;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.memberProject);
	}

	@Override
	public void validate() {
		super.validateObject(this.memberProject);
	}

	@Override
	public void execute() {
		this.repository.save(this.memberProject);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.memberProject);

		int projectId = super.getRequest().getData("projectId", int.class);

		String role = super.getRequest().getData("role", String.class);

		Collection<?> unassignedMembers = null;

		if ("INVENTOR".equals(role))
			unassignedMembers = this.memberRepo.findUnassignedInventors(projectId);
		else if ("SPOKESPERSON".equals(role))
			unassignedMembers = this.memberRepo.findUnassignedSpokespersons(projectId);
		else if ("FUNDRAISER".equals(role))
			unassignedMembers = this.memberRepo.findUnassignedFundraisers(projectId);

		super.unbindGlobal("listaMiembros", unassignedMembers);
		super.unbindGlobal("projectId", projectId);
		super.unbindGlobal("role", role);
	}

}
