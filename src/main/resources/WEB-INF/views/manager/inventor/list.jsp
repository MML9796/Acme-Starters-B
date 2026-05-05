<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="manager.inventor.list.label.username" path="userAccount.username"/>
	<acme:list-column code="manager.inventor.list.label.bio" path="bio"/>
	<acme:list-hidden path="keyWords"/>
	<acme:list-hidden  path="licensed"/>
</acme:list>

<jstl:if test="${draftMode == true}">
<acme:button code="manager.memberProject.button.assignNew" action="/manager/member-project/create?projectId=${projectId}&role=INVENTOR"/>

<acme:button code="manager.memberProject.button.unAssignNew" action="/manager/member-project/delete?projectId=${projectId}&role=INVENTOR"/>
</jstl:if>