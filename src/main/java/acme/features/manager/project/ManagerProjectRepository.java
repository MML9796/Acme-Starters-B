
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.invention.Invention;
import acme.entities.member.MemberProject;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select i from Project i where i.id = :id")
	Project findProjectById(int id);

	@Query("select i from Project i where i.manager.id = :id")
	Collection<Project> findAllProjectByManagerId(int id);

	@Query("select i from Invention i where i.project.id = :id")
	Collection<Invention> findInventionsByProjectId(int id);

	@Query("select c from Campaign c where c.project.id = :id")
	Collection<Campaign> findCampaignsByProjectId(int id);

	@Query("select s from Strategy s where s.project.id = :id")
	Collection<Strategy> findStrategiesByProjectId(int id);

	@Query("select m from MemberProject m where m.project.id = :id")
	Collection<MemberProject> findMemberProjectsByProjectId(int id);
}
