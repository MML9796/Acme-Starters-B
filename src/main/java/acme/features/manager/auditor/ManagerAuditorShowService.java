
package acme.features.manager.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Auditor;
import acme.realms.Manager;

@Service
public class ManagerAuditorShowService extends AbstractService<Manager, Auditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerAuditorRepository	repository;

	private Auditor						auditor;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditor = this.repository.findAuditorById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int id;

		id = super.getRequest().getData("id", int.class);
		status = this.auditor != null && this.repository.findPublishedAuditReportsCountByAuditorId(id) > 0;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditor, "identity.name", "identity.surname", "identity.email", "firm", "highlights", "solicitor");
	}
}
