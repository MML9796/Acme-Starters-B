
package acme.features.manager.memberProject;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
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

	@Query("select f from Fundraiser f where f.userAccount.id not in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Fundraiser> findUnassignedFundraisers(int projectId);

	@Query("select m from Member m where m.userAccount.id = :id")
	Member findOneByUserAccountId(int id);

	@Query("select i from Inventor i where i.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Inventor> findAssignedInventors(int projectId);

	@Query("select s from Spokesperson s where s.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Spokesperson> findAssignedSpokespersons(int projectId);

	@Query("select f from Fundraiser f where f.userAccount.id in (" + "select ua.id from MemberProject mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	Collection<Fundraiser> findAssignedFundraisers(int projectId);

	@Query("select f.userAccount from Fundraiser f where f.id = :id")
	UserAccount findUserAccountByFundraiserId(int id);

	@Query("select i.userAccount from Inventor i where i.id = :id")
	UserAccount findUserAccountByInventorId(int id);

	@Query("select s.userAccount from Spokesperson s where s.id = :id")
	UserAccount findUserAccountBySpokespersonId(int id);

}
