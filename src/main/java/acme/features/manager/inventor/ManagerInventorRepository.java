
package acme.features.manager.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Inventor;

@Repository
public interface ManagerInventorRepository extends AbstractRepository {

	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);

	@Query("select i from Inventor i where i.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Inventor> findAllInventor(int projectId);

	@Query("select i from Inventor i where i.userAccount.id not in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Inventor> findUnassignedInventors(int projectId);

	@Query("select i.inventor from Invention i where i.id = :inventorId")
	Inventor findOneInventorByInventionId(int inventorId);
}
