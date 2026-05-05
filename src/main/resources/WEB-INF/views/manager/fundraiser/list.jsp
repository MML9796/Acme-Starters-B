<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list show="/manager/fundraiser/show?projectId=${projectId}&id=${id}">
    <acme:list-column code="manager.memberProject.list.label.username" path="userAccount.username"/>
    <acme:list-column code="manager.memberProject.list.label.bank" path="bank"/>
    <acme:list-hidden  path="agent"/>
    <acme:list-hidden path="statement"/>
    
</acme:list>

<acme:button code="manager.memberProject.button.assignNew" action="/manager/member-project/create?projectId=${projectId}&role=FUNDRAISER"/>

<acme:button code="manager.memberProject.button.unAssignNew" action="/manager/member-project/delete?projectId=${projectId}&role=FUNDRAISER"/>