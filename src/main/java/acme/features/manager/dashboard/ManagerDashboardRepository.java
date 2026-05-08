
package acme.features.manager.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Project p where p.manager.id = :managerId")
	Double numberOfProjects(int managerId);

	@Query("select avg(select count(p) from Project p where p.manager.id != m.id) from Manager m where m.id != :managerId")
	Double averageOfProjectsOfOtherManagers(int managerId);

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> findProjectsByManagerId(int managerId);

}
