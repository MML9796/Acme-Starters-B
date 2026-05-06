<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
    <acme:form-textbox code="manager.projects.form.label.title" path="title"/>
    <acme:form-textbox code="manager.projects.form.label.keyWords" path="keyWords"/>   
    <acme:form-moment code="manager.projects.form.label.kickOffMoment" path="kickOffMoment"/>
    <acme:form-moment code="manager.projects.form.label.closeOutMoment" path="closeOutMoment"/>   
    <acme:form-textarea code="manager.projects.form.label.description" path="description"/>
   
    <jstl:if test="${_command != 'create'}">
    	<acme:form-double code="manager.projects.form.label.effort" path="effort" readonly="true"/>
    </jstl:if>
    
    <jstl:choose>
    	<jstl:when test="${_command == 'create' }">
        	<acme:submit code="manager.projects.button.create" action="/manager/project/create"/>
    	</jstl:when>
    	
    	<jstl:when test="${acme:anyOf(_command, 'show|update|delete') }">
        	
        	<jstl:if test="${draftMode == true}">
            	<acme:submit code="manager.projects.button.update" action="/manager/project/update"/>
            	<acme:submit code="manager.projects.button.delete" action="/manager/project/delete"/>
        	</jstl:if>
        	<br/>
	
	<div>
		<h4><spring:message code="manager.projects.label.roles"/></h4>
        <button type="button" class="btn btn-info" onclick="javascript: window.location.href='manager/inventor/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.inventor"/>
	    </button>
	    
	    <button type="button" class="btn btn-info" onclick="javascript: window.location.href='manager/spokesperson/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.spokesperson"/>
	    </button>
	    
	    <button type="button" class="btn btn-info" onclick="javascript: window.location.href='manager/fundraiser/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.fundraiser"/>
	    </button>
	    
	</div>
	<br/>
	
	<div>
		<h4><spring:message code="manager.projects.label.elements"/></h4>
		
		<button type="button" class="btn btn-success" onclick="javascript: window.location.href='manager/invention/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.invention"/>
	    </button>
	    
	    
        <button type="button" class="btn btn-success" onclick="javascript: window.location.href='manager/campaign/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.campaign"/>
	    </button>

	    
	    <button type="button" class="btn btn-success" onclick="javascript: window.location.href='manager/strategy/list?projectId=${id}';">
	        <spring:message code="manager.projects.button.strategy"/>
	    </button>
	    <jstl:if test="${draftMode == false}">
            <button type="button" class="btn btn-success" onclick="javascript: window.location.href='manager/sponsorship/list?projectId=${id}';">
		        <spring:message code="manager.projects.button.sponsorship"/>
		    </button>
		    
		    <button type="button" class="btn btn-success" onclick="javascript: window.location.href='manager/audit-report/list?projectId=${id}';">
		        <spring:message code="manager.projects.button.auditReport"/>
		    </button>
	</jstl:if>
	</div>
    	</jstl:when>
	</jstl:choose>
	
	
	
   
</acme:form>