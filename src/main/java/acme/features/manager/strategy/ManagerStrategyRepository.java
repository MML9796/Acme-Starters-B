
package acme.features.manager.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;

@Repository
public interface ManagerStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select s from Strategy s")
	Collection<Strategy> findAllStrategies();

	@Query("select s from Strategy s where s.project.id = :projectId")
	Collection<Strategy> findStrategyByProjectId(int projectId);

}
