
package acme.features.manager.audit_report;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audit_reports.AuditReport;
import acme.entities.audit_reports.AuditSection;
import acme.entities.projects.Project;

@Repository
public interface ManagerAuditReportRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select ar from AuditReport ar where ar.project.id = :projectId")
	Collection<AuditReport> findAuditReportsByProjectId(int projectId);

	@Query("select ar from AuditReport ar where ar.id = :id")
	AuditReport findAuditReportById(int id);

	@Query("select ar from AuditReport ar where ar.ticker = :ticker")
	AuditReport findAuditReportByTicker(String ticker);

	@Query("select count(s) from AuditSection s where s.auditReport.id = :id")
	int findAuditSectionsSizeById(int id);

	@Query("select s from AuditSection s where s.auditReport.id = :auditReportId")
	Collection<AuditSection> findSectionsByAuditReportId(int auditReportId);

}
