
package acme.features.manager.memberProject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.member.MemberProject;

@Repository
public interface ManagerMemberProjectRepository extends AbstractRepository {

	@Query("select mp from MemberProject mp where mp.id = :id")
	MemberProject findOneById(int id);
	@Query("select mp from MemberProject mp where mp.project.id = :projectId and mp.member.userAccount.id = :userAccountId")
	MemberProject findOneByProjectIdAndUserAccountId(int projectId, int userAccountId);
}
