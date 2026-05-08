
package acme.features.member.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.tactic.Tactic;
import acme.realms.Member;

@Controller
public class MemberTacticController extends AbstractController<Member, Tactic> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", MemberTacticListService.class);
		super.addBasicCommand("show", MemberTacticShowService.class);
	}
}
