<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="manager.strategy.form.label.ticker" path="ticker"/>
    <acme:form-textbox code="manager.strategy.form.label.name" path="name"/>   
    <acme:form-moment code="manager.strategy.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="manager.strategy.form.label.endMoment" path="endMoment"/>   
    <acme:form-textarea code="manager.strategy.form.label.description" path="description"/>
    <acme:form-textbox code="manager.strategy.form.label.moreInfo" path="moreInfo"/>
    <acme:form-double code="manager.strategy.form.label.monthsActive" path="monthsActive"/>
    <acme:form-double code="manager.strategy.form.label.expectedPercentage" path="expectedPercentage"/>
    <acme:button code="manager.strategy.form.label.tactics" action="/manager/tactic/list?strategyId=${id}"/>
</acme:form>