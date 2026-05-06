<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>

    <acme:list-column code="manager.strategy.list.label.ticker" path="ticker"/>
    <acme:list-column code="manager.strategy.list.label.name" path="name"/>
    <acme:list-column code="manager.strategy.list.label.startMoment" path="startMoment"/>
    <acme:list-column code="manager.strategy.list.label.endMoment" path="endMoment"/>
    <acme:list-hidden path="monthsActive"/>
	<acme:list-hidden path="expectedPercentage"/>
	<acme:list-hidden path="moreInfo"/>
</acme:list>