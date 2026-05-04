<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.audit-report.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="manager.audit-report.form.label.name" path="name"/>
	<acme:form-textarea code="manager.audit-report.form.label.description" path="description"/>
	<acme:form-moment code="manager.audit-report.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.audit-report.form.label.endMoment" path="endMoment"/>
	<acme:form-url code="manager.audit-report.form.label.moreInfo" path="moreInfo"/>
	<acme:form-double code="manager.audit-report.form.label.monthsActive" path="monthsActive"/>
	<acme:form-integer code="manager.audit-report.form.label.hours" path="hours"/>
	
	<acme:button code="manager.audit-report.form.label.audit-sections" action="/manager/audit-section/list?auditReportId=${id}"/>
</acme:form>

