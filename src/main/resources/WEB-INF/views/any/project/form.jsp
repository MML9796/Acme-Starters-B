<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.project.form.label.title" path="title"/>
	<acme:form-textarea code="any.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="any.project.form.label.description" path="description"/>
	<acme:form-moment code="any.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="any.project.form.label.closeOutMoment" path="closeOutMoment"/>
	<acme:form-double code="any.project.form.label.effort" path="effort"/>
	<acme:button code="any.project.button.manager" action="/any/manager/show?id=${managerId}"/>
	<acme:button code="any.project.button.inventor" action="/any/inventor/list?projectId=${id}"/>
	<acme:button code="any.project.button.spokesperson" action="/any/spokesperson/list?projectId=${id}"/>
	<acme:button code="any.project.button.fundraiser" action="/any/fundraiser/list?projectId=${id}"/>
	<acme:button code="any.project.button.invention" action="/any/invention/list?projectId=${id}"/>
	<acme:button code="any.project.button.strategy" action="/any/strategy/list?projectId=${id}"/>
	<acme:button code="any.project.button.campaign" action="/any/campaign/list?projectId=${id}"/>
	<acme:button code="any.project.button.sponsorship" action="/any/sponsorship/list?projectId=${id}"/>
     
    
</acme:form>