
package acme.features.manager.memberProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.member.MemberProject;
import acme.realms.Manager;

@Service
public class ManagerMemberProjectDeleteService extends AbstractService<Manager, MemberProject> {

	@Autowired
	private ManagerMemberProjectRepository	repository;

	private MemberProject					memberProject;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.memberProject = this.repository.findOneById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int managerId = super.getRequest().getPrincipal().getAccountId();
		status = this.memberProject != null && this.memberProject.getProject().getManager().getId() == managerId;

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
		this.repository.delete(this.memberProject);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.memberProject);
	}

}
