<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="manager.tactic.list.label.name" path="name"/>
    <acme:list-column code="manager.tactic.list.label.expectedPercentage" path="expectedPercentage"/>
    <acme:list-column code="manager.tactic.list.label.kind" path="kind"/>
    <acme:list-hidden path="notes"/>
</acme:list>