
package acme.features.member.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface MemberProjectRepository extends AbstractRepository {

	@Query("select mp.project from MemberProject mp where mp.member.id = :memberId")
	Collection<Project> findProjectsByMemberId(int memberId);

	@Query("select count(mp) from MemberProject mp where mp.project.id = :projectId and mp.member.id = :memberId")
	Integer checkProjectBelongsToMember(int projectId, int memberId);

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select count(mp) from MemberProject mp where mp.project.id = :projectId and mp.member.userAccount.id = :accountId")
	Integer checkProjectBelongsToAccountId(int projectId, int accountId);
}
