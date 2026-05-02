
package acme.entities.projects;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.strategy.Strategy;

@Repository
public interface ProjectRepository extends AbstractRepository {

	@Query("select i from Invention i where i.project.id = :id")
	List<Invention> getProjectInventionsById(int id);
	@Query("select c from Campaign c where c.project.id = :id")
	List<Campaign> getProjectCampaignsById(int id);
	@Query("select s from Strategy s where s.project.id = :id")
	List<Strategy> getProjectStrategiesById(int id);

	@Query("select count(pm) from MemberProject pm where pm.project.id = :id")
	Integer getProjectMemberCountById(int id);

}
