
package acme.features.sponsor.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface SponsorProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select distinct p from Project p join Sponsorship s on s.project = p where s.sponsor.id = :sponsorId")
	Collection<Project> findProjectsBySponsorId(int sponsorId);

	@Query(" select case when count(s) > 0 then true else false end from Sponsorship s where s.project.id = :projectId and s.sponsor.id = :sponsorId ")
	boolean existsSponsorshipBySponsorIdAndProjectId(int projectId, int sponsorId);
}
