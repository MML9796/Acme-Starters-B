
package acme.features.auditor.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface AuditorProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select distinct p from Project p join AuditReport a on a.project = p where a.auditor.id = :auditorId")
	Collection<Project> findProjectsByAuditorId(int auditorId);

	@Query(" select case when count(a) > 0 then true else false end from AuditReport a where a.project.id = :projectId and a.auditor.id = :auditorId ")
	boolean existsAuditreportByAuditorIdAndProjectId(int projectId, int auditorId);
}
