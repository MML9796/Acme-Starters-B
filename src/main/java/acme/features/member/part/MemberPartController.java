
package acme.features.member.part;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.part.Part;
import acme.realms.Member;

@Controller
public class MemberPartController extends AbstractController<Member, Part> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", MemberPartListService.class);
		super.addBasicCommand("show", MemberPartShowService.class);
	}
}
