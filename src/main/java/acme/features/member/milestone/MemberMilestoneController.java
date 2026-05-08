
package acme.features.member.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.milestones.Milestone;
import acme.realms.Member;

@Controller
public class MemberMilestoneController extends AbstractController<Member, Milestone> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", MemberMilestoneListService.class);
		super.addBasicCommand("show", MemberMilestoneShowService.class);
	}

}
