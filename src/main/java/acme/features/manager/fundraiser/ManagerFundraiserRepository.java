
package acme.features.manager.fundraiser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Fundraiser;

@Repository
public interface ManagerFundraiserRepository extends AbstractRepository {

	@Query("select f from Fundraiser f where f.id = :id")
	Fundraiser findOneFundraiserById(int id);

	@Query("select f from Fundraiser f where f.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Fundraiser> findAllFundraiser(int projectId);

	@Query("select f from Fundraiser f where f.userAccount.id not in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Fundraiser> findUnassignedFundraisers(int projectId);

	@Query("select s.fundraiser from Strategy s where s.id = :strategyId")
	Fundraiser findOneFundraiserByStrategyId(int strategyId);
}
