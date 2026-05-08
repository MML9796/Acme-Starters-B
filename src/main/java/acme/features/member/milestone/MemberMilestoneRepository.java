
package acme.features.member.milestone;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.milestones.Milestone;

public interface MemberMilestoneRepository extends AbstractRepository {

	@Query("select m from Milestone m where m.campaign.id = :id")
	Collection<Milestone> findAllMilestoneByCampaignId(int id);

	@Query("select m from Milestone m where m.id=:id")
	Milestone findMilestoneById(int id);
}
