<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<acme:form>

    <input type="hidden" name="projectId" value="${projectId}"/>
    <input type="hidden" name="role" value="${role}"/>
    <acme:form-select path="member" code="manager.memberProject.form.label.selectMember" choices="${listaMiembros}"/>
    
    <br/>

    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="manager.memberProject.button.assign" 
                         action="/manager/member-project/create?projectId=${projectId}&role=${role}"/>
        </jstl:when>

        <jstl:when test="${_command == 'delete'}">
            <acme:submit code="manager.memberProject.button.unAssignNew" 
                         action="/manager/member-project/delete?projectId=${projectId}&role=${role}"/>
        </jstl:when>
    </jstl:choose>
    
    <br/>
    <hr/>
    
</acme:form>