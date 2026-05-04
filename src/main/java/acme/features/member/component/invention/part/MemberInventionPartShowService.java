
package acme.features.member.component.invention.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.invention.Invention;
import acme.entities.part.Part;
import acme.entities.part.Part.PartKind;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Member;

@Service
public class MemberInventionPartShowService extends AbstractService<Member, Part> {

	//Internal state
	@Autowired
	private MemberInventionPartRepository	repository;
	@Autowired
	private MemberProjectRepository			projectRepository;

	private Part							part;
	private Invention						invention;


	//AbstractService interface
	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.part = this.repository.findPartById(id);

		if (this.part != null)
			this.invention = this.part.getInvention();
		else
			this.invention = null;
	}

	@Override
	public void authorise() {
		boolean status = false;
		int memberId;

		memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		if (this.invention != null) {

			int projectId = this.invention.getProject().getId();

			Integer count = this.projectRepository.checkProjectBelongsToMember(projectId, memberId);

			status = count != null && count > 0;
		}

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
		super.unbindGlobal("draftMode", this.part.getInvention().getDraftMode());
		super.unbindGlobal("id", this.part.getId());
		SelectChoices opcionesKind = SelectChoices.from(PartKind.class, this.part.getKind());
		super.unbindGlobal("listaKinds", opcionesKind);
	}
}
