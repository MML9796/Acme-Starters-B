<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.spokesperson.list.label.username" path="userAccount.username"/>
	<acme:list-column code="manager.spokesperson.list.label.cv" path="cv"/>
	<acme:list-hidden path="achievements"/>
	<acme:list-hidden path="licensed"/>
</acme:list>


<acme:button code="manager.memberProject.button.assignNew" action="/manager/member-project/create?projectId=${projectId}&role=SPOKESPERSON"/>

<acme:button code="manager.memberProject.button.unAssignNew" action="/manager/member-project/delete?projectId=${projectId}&role=SPOKESPERSON"/>
