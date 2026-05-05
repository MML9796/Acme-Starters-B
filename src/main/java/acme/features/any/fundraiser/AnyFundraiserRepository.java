
package acme.features.any.fundraiser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Fundraiser;

@Repository
public interface AnyFundraiserRepository extends AbstractRepository {

	@Query("select f from Fundraiser f where f.id = :id")
	Fundraiser findOneFundraiserById(int id);

	@Query("""
		    select f
		    from Fundraiser f
		    where f.userAccount.id in (
		        select ua.id
		        from MemberProject mp
		        join mp.member m
		        join m.userAccount ua
		        where mp.project.id = :projectId
		    )
		""")
	Collection<Fundraiser> findAllFundraisersByProjectId(int projectId);
}
