
package acme.features.manager.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.milestones.Milestone;
import acme.realms.Manager;

@Service
public class ManagerMilestoneShowService extends AbstractService<Manager, Milestone> {

	@Autowired
	private ManagerMilestoneRepository	repository;

	private Milestone					milestone;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		super.setAuthorised(this.milestone != null);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
	}
}
