
package acme.features.member.component.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invention.Invention;

@Repository
public interface MemberInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.project.id = :id")
	Collection<Invention> findAllInventionByProjectId(int id);

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

	@Query("select count(mp) from MemberProject mp where mp.project.id = :projectId and mp.member.id = :memberId")
	Integer checkProjectBelongsToMember(int projectId, int memberId);
}
