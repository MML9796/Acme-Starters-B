<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:form-textbox code="member.project.form.label.title" path="title"/>
	<acme:form-textbox code="member.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="member.project.form.label.description" path="description"/>
	<acme:form-moment code="member.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="member.project.form.label.closeOutMoment" path="closeOutMoment"/>
    <acme:form-double code="member.project.form.label.effort" path="effort" readonly="true"/>

	<br/>
	<hr/>

	<div>
		<h4><spring:message code="member.project.label.roles"/></h4>
		
		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='any/manager/show?id=${managerId}';">
			<spring:message code="any.project.button.manager"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='member/inventor/list?projectId=${id}';">
			<spring:message code="member.project.button.inventors"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='member/spokesperson/list?projectId=${id}';">
			<spring:message code="member.project.button.spokesperson"/>
		</button>

		<button type="button" class="btn btn-info"
			onclick="javascript: window.location.href='member/fundraiser/list?projectId=${id}';">
			<spring:message code="member.project.button.fundraiser"/>
		</button>

	</div>

	<br/>

	<div>
		<h4><spring:message code="member.project.label.elements"/></h4>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='member/invention/list?projectId=${id}';">
			<spring:message code="member.project.button.inventions"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='member/campaign/list?projectId=${id}';">
			<spring:message code="member.project.button.campaigns"/>
		</button>

		<button type="button" class="btn btn-success"
			onclick="javascript: window.location.href='member/strategy/list?projectId=${id}';">
			<spring:message code="member.project.button.strategies"/>
		</button>

		<jstl:if test="${!draftMode}">
			<button type="button" class="btn btn-success"
				onclick="javascript: window.location.href='any/sponsorship/list?projectId=${id}';">
				<spring:message code="any.project.button.sponsorship"/>
			</button>

			<button type="button" class="btn btn-success"
				onclick="javascript: window.location.href='any/audit-report/list?projectId=${id}';">
				<spring:message code="any.project.button.audit-report"/>
			</button>
		</jstl:if>
	</div>

</acme:form>