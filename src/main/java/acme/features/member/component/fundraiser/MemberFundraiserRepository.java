
package acme.features.member.component.fundraiser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Fundraiser;

@Repository
public interface MemberFundraiserRepository extends AbstractRepository {

	@Query("select f from Fundraiser f where f.id = :id")
	Fundraiser findOneFundraiserById(int id);

	@Query("select f from Fundraiser f where f.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Fundraiser> findAllFundraiser(int projectId);

	@Query("select count(s) from Strategy s, MemberProject mp where s.project.id = mp.project.id and mp.member.id = :memberId and s.fundraiser.id = :fundraiserId")
	Integer checkFundraiserBelongsToMember(int fundraiserId, int memberId);
}
