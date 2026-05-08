
package acme.forms;

import acme.client.components.basis.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double						numberOfProjects;
	Double						deviationOfNumberOfProjectsWrtAvgOfOtherManagers;
	Double						mimimumEffortOfProjects;
	Double						maximumEffortOfProjects;
	Double						averageEffortOfProjects;
	Double						averageAbsoulteDeviationOfEffortOfProjects;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
