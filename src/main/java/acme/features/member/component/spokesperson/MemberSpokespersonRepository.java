
package acme.features.member.component.spokesperson;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Spokesperson;

@Repository
public interface MemberSpokespersonRepository extends AbstractRepository {

	@Query("select s from Spokesperson s where s.id = :id")
	Spokesperson findOneSpokespersonById(int id);

	@Query("select s from Spokesperson s where s.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Spokesperson> findAllSpokesperson(int projectId);

	@Query("select count(c) from Campaign c, MemberProject mp where c.project.id = mp.project.id and mp.member.id = :memberId and c.spokesperson.id = :spokespersonId")
	Integer checkSpokespersonBelongsToMember(int spokespersonId, int memberId);
}
