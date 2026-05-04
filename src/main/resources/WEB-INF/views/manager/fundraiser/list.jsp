<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="manager.memberProject.list.label.username" path="member.userAccount.username"/>
    <acme:list-column code="manager.memberProject.list.label.bank" path="member.bank"/>
    <acme:list-column code="manager.memberProject.list.label.agent" path="member.agent"/>
</acme:list>
<acme:button code="manager.memberProject.button.assignNew" action="/manager/member-project/create?projectId=${projectId}&role=FUNDRAISER"/>