
package acme.features.manager.audit_section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.SectionKind;
import acme.entities.audit_reports.AuditSection;
import acme.realms.Manager;

@Service
public class ManagerAuditSectionShowService extends AbstractService<Manager, AuditSection> {

	@Autowired
	private ManagerAuditSectionRepository	repository;

	private AuditSection					auditSection;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditSection = this.repository.findAuditSectionById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.auditSection != null && this.auditSection.getAuditReport() != null && this.auditSection.getAuditReport().getProject() != null && this.auditSection.getAuditReport().getProject().getManager().getId() == managerId
			&& !this.auditSection.getAuditReport().getProject().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(SectionKind.class, this.auditSection.getKind());

		tuple = super.unbindObject(this.auditSection, "name", "notes", "hours", "kind", "auditReport");
		tuple.put("kinds", choices);
	}

}
