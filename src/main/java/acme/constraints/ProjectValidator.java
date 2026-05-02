
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.projects.Project;

@Validator
public class ProjectValidator extends AbstractValidator<ValidProject, Project> {

	// ConstraintValidator interface ------------------------------------------

	@Override
	protected void initialise(final ValidProject annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Project project, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		if (project == null)
			result = true;
		else {
			{
				if (project.getKickOffMoment() != null && project.getCloseOutMoment() != null) {
					boolean correctStartEndFutureInterval;

					correctStartEndFutureInterval = MomentHelper.isAfter(project.getCloseOutMoment(), project.getKickOffMoment());

					super.state(context, correctStartEndFutureInterval, "kickOffMoment", "acme.validation.project.invalid-start-end-interval.message");
				}
			}
			result = !super.hasErrors(context);
		}
		return result;
	}

}
