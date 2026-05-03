
package acme.features.any.spokesperson;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Spokesperson;

@Repository
public interface AnySpokespersonRepository extends AbstractRepository {

	@Query("select s from Spokesperson s where s.id=:id")
	Spokesperson findSpokespersonById(int id);

	@Query("""
		    select s
		    from Spokesperson s
		    where s.userAccount.id in (
		        select ua.id
		        from MemberProject mp
		        join mp.member m
		        join m.userAccount ua
		        where mp.project.id = :projectId
		    )
		""")
	Collection<Spokesperson> findAllSpokespeopleByProjectId(int projectId);
}
