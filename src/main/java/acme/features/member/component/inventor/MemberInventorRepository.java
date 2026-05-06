
package acme.features.member.component.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Inventor;

@Repository
public interface MemberInventorRepository extends AbstractRepository {

	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);

	@Query("select i from Inventor i where i.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Inventor> findAllInventor(int projectId);

	@Query("select count(i) from Invention i, MemberProject mp where i.project.id = mp.project.id and mp.member.id = :memberId and i.inventor.id = :inventorId")
	Integer checkInventorBelongsToMember(int inventorId, int memberId);
}
