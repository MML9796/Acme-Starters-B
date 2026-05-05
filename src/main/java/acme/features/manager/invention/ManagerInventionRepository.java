
package acme.features.manager.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;

@Repository
public interface ManagerInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

	@Query("select i from Invention i")
	Collection<Invention> findAllInventions();

	@Query("select i from Invention i where i.project.id = :projectId")
	Collection<Invention> findInventionsByProjectId(int projectId);

}
