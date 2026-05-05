
package acme.features.manager.donation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.donation.Donation;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface ManagerDonationRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findSponsorshipById(int id);

	@Query("select d from Donation d where d.sponsorship.id = :id")
	Collection<Donation> findAllDonationBySponsorshipId(int id);

	@Query("select d from Donation d where d.id=:id")
	Donation findDonationById(int id);
}
