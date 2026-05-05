
package acme.features.manager.memberProject;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.member.MemberProject;
import acme.realms.Manager;

@Controller
public class ManagerMemberProjectController extends AbstractController<Manager, MemberProject> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("create", ManagerMemberProjectCreateService.class);
		super.addBasicCommand("delete", ManagerMemberProjectDeleteService.class);
	}

}
