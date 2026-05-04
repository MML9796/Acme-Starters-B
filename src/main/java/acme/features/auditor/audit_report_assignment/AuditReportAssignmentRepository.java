
package acme.features.auditor.audit_report_assignment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;

@Repository
public interface AuditReportAssignmentRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select a from AuditReport a where a.id = :id")
	AuditReport findAuditReportById(int id);

	@Query("select a from AuditReport a where a.auditor.id = :auditorId and a.project is null and a.draftMode = false ")
	Collection<AuditReport> findAvailableAuditReportsByAuditorId(int auditorId);
}
