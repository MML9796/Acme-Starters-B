<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<acme:form>
    
    <input type="hidden" name="projectId" value="${projectId}"/>
    <input type="hidden" name="role" value="${role}"/>

    <jstl:choose>
    	<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<acme:form>
    
    <input type="hidden" name="projectId" value="${projectId}"/>
    <input type="hidden" name="role" value="${role}"/>

    <jstl:choose>
    	<jstl:when test="${_command == 'create' }">
            
            <div>
        <form:label path="squadMember">
            <spring:message code="manager.memberProject.form.label.selectMember"/>
        </form:label>
        <br/>
        <form:select path="squadMember" items="${listaMiembros}" itemLabel="userAccount.username" itemValue="id" />
        <form:errors path="squadMember" cssStyle="color: red"/>
    </div>
            <br/>
            <acme:submit code="manager.memberProject.button.assign" action="/manager/member-project/create"/>
    	</jstl:when>
    </jstl:choose>
    
    <br/>
    <hr/>
    
    <div>
        <button type="button" class="btn btn-info" onclick="javascript: window.location.replace('manager/${role.toLowerCase()}/list?projectId=${projectId}');">
            <spring:message code="manager.memberProject.button.return"/>
        </button>
    </div>

</acme:form>
    </jstl:choose>
    
    <br/>
    <hr/>
    
    <div>
        <button type="button" class="btn btn-info" onclick="javascript: window.location.replace('manager/${role.toLowerCase()}/list?projectId=${projectId}');">
            <spring:message code="manager.memberProject.button.return"/>
        </button>
    </div>

</acme:form>