
package acme.features.manager.spokesperson;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Spokesperson;

@Repository
public interface ManagerSpokespersonRepository extends AbstractRepository {

	@Query("select s from Spokesperson s where s.id = :id")
	Spokesperson findOneSpokespersonById(int id);

	@Query("select s from Spokesperson s where s.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Spokesperson> findAllSpokesperson(int projectId);

	@Query("select s from Spokesperson s where s.userAccount.id not in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Spokesperson> findUnassignedSpokespersons(int projectId);

	@Query("select c.spokesperson from Campaign c where c.id = :campaignId")
	Spokesperson findOneSpokespersonByCampaignId(int campaignId);
}
