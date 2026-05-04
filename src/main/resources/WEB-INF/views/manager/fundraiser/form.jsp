<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
    <input type="hidden" name="projectId" value="${projectId}"/>

    <acme:form-textbox code="manager.fundraiser.form.label.username" path="squadMember.userAccount.username" readonly="true"/>
    <acme:form-textbox code="manager.fundraiser.form.label.bank" path="squadMember.bank" readonly="true"/>
    <acme:form-textbox code="manager.fundraiser.form.label.agent" path="squadMember.agent" readonly="true"/>

    <br/>
    
    <jstl:choose>
        <jstl:when test="${_command == 'show'}">
            <acme:submit code="manager.fundraiser.button.unassign" action="/manager/fundraiser/delete"/>
        </jstl:when>
    </jstl:choose>

    <br/>
    <hr/>
</acme:form>