<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:form-textbox code="any.project.form.label.title" path="title"/>
	<acme:form-textarea code="any.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="any.project.form.label.description" path="description"/>
	<acme:form-moment code="any.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="any.project.form.label.closeOutMoment" path="closeOutMoment"/>
	<acme:form-double code="any.project.form.label.effort" path="effort"/>

	<br/>
	<hr/>

	<div>
		<h4><spring:message code="any.project.label.roles"/></h4>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='any/manager/show?id=${managerId}';">
			<spring:message code="any.project.button.manager"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='any/inventor/list?projectId=${id}';">
			<spring:message code="any.project.button.inventor"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='any/spokesperson/list?projectId=${id}';">
			<spring:message code="any.project.button.spokesperson"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='any/fundraiser/list?projectId=${id}';">
			<spring:message code="any.project.button.fundraiser"/>
		</button>
	</div>

	<br/>

	<div>
		<h4><spring:message code="any.project.label.elements"/></h4>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='any/invention/list?projectId=${id}';">
			<spring:message code="any.project.button.invention"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='any/strategy/list?projectId=${id}';">
			<spring:message code="any.project.button.strategy"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='any/campaign/list?projectId=${id}';">
			<spring:message code="any.project.button.campaign"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='any/sponsorship/list?projectId=${id}';">
			<spring:message code="any.project.button.sponsorship"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='any/audit-report/list?projectId=${id}';">
			<spring:message code="any.project.button.audit-report"/>
		</button>
	</div>

</acme:form>