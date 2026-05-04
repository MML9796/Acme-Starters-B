
package acme.features.manager.memberProject;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Repository
public interface ManagerMemberRepository extends AbstractRepository {

	@Query("select m from Member m where m.id = :id")
	Member findOneById(int id);

	@Query("select i from Inventor i where i.id not in (select mp.member.id from MemberProject mp where mp.project.id = :projectId)")
	Collection<Inventor> findUnassignedInventors(int projectId);

	@Query("select s from Spokesperson s where s.id not in (select mp.member.id from MemberProject mp where mp.project.id = :projectId)")
	Collection<Spokesperson> findUnassignedSpokespersons(int projectId);

	@Query("select f from Fundraiser f where f.id not in (select mp.member.id from MemberProject mp where mp.project.id = :projectId)")
	Collection<Fundraiser> findUnassignedFundraisers(int projectId);
}
