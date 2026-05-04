<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="member.project.form.label.title" path="title"/>
	<acme:form-textbox code="member.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="member.project.form.label.description" path="description"/>
	<acme:form-moment code="member.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="member.project.form.label.closeOutMoment" path="closeOutMoment"/>
    <acme:form-double code="member.project.form.label.effort" path="effort" readonly="true"/>
        
    <acme:button code="member.project.button.inventions" action="/member/invention/list?projectId=${id}"/>
    <acme:button code="member.project.button.campaigns" action="/member/campaign/list?projectId=${id}"/>
    <acme:button code="member.project.button.strategies" action="/member/strategy/list?projectId=${id}"/>
    <acme:button code="member.project.button.inventors" action="/member/inventor/list?projectId=${id}"/>
    <acme:button code="member.project.button.spokesperson" action="/member/spokesperson/list?projectId=${id}"/>
    <acme:button code="member.project.button.fundraiser" action="/member/fundraiser/list?projectId=${id}"/>

 
</acme:form>