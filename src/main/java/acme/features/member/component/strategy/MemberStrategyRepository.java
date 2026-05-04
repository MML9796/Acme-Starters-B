
package acme.features.member.component.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;

@Repository
public interface MemberStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.project.id = :id")
	Collection<Strategy> findAllStrategyByProjectId(int id);

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select count(mp) from MemberProject mp where mp.project.id = :projectId and mp.member.id = :memberId")
	Integer checkProjectBelongsToMember(int projectId, int memberId);
}
