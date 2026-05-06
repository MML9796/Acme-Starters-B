
package acme.features.member.component.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;

@Repository
public interface MemberCampaignRepository extends AbstractRepository {

	@Query("select c from Campaign c where c.project.id = :id")
	Collection<Campaign> findAllCampaignByProjectId(int id);

	@Query("select c from Campaign c where c.id = :id")
	Campaign findCampaignById(int id);

	@Query("select count(mp) from MemberProject mp where mp.project.id = :projectId and mp.member.id = :memberId")
	Integer checkProjectBelongsToMember(int projectId, int memberId);
}
