
package acme.features.manager.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Sponsor;

@Repository
public interface ManagerSponsorRepository extends AbstractRepository {

	@Query("select s from Sponsor s where s.id=:id")
	Sponsor findSponsorById(int id);

}
