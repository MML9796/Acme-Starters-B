
package acme.entities.projects;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.constraints.ValidProject;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;
import acme.validation.ValidHeader;
import acme.validation.ValidText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidProject
public class Project extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidHeader
	@Column
	private String				title;

	@Mandatory
	@ValidText
	@Column
	private String				keyWords;

	@Mandatory
	@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				kickOffMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				closeOutMoment;

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	// Derived attributes -----------------------------------------------------

	@Transient
	@Autowired
	private ProjectRepository	repository;


	@Mandatory
	@Valid
	@Transient
	public Double getEffort() {
		// Very inefficient, but needed since monthsActive is transient
		Double result;

		Double totalEffort;
		List<Invention> inventions;
		List<Campaign> campaigns;
		List<Strategy> strategies;

		inventions = this.repository.getProjectInventionsById(this.getId());
		campaigns = this.repository.getProjectCampaignsById(this.getId());
		strategies = this.repository.getProjectStrategiesById(this.getId());
		totalEffort = inventions.stream().mapToDouble(i -> i.getMonthsActive()).sum() + campaigns.stream().mapToDouble(c -> c.getMonthsActive()).sum() + strategies.stream().mapToDouble(s -> s.getMonthsActive()).sum();

		Integer involvedCount;
		Integer wrapper;

		wrapper = this.repository.getProjectMemberCountById(this.getId());
		involvedCount = wrapper == null ? 0 : wrapper;

		result = involvedCount > 0 ? totalEffort / involvedCount : null;

		return result;
	}

	// Relationships ----------------------------------------------------------


	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Manager manager;

}
