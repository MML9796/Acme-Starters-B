<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="fundraiser.strategy.form.label.ticker" path="ticker"/>
    <acme:form-textbox code="fundraiser.strategy.form.label.name" path="name"/>   
    <acme:form-moment code="fundraiser.strategy.form.label.startMoment" path="startMoment"/>
    <acme:form-moment code="fundraiser.strategy.form.label.endMoment" path="endMoment"/>   
    <acme:form-textarea code="fundraiser.strategy.form.label.description" path="description"/>
    <acme:form-url code="fundraiser.strategy.form.label.moreInfo" path="moreInfo"/>
     <acme:form-double code="fundraiser.strategy.form.label.monthsActive" path="monthsActive" readonly="true"/>
    <acme:form-double code="fundraiser.strategy.form.label.expectedPercentage" path="expectedPercentage"  readonly="true"/>
    
   	<acme:button code="fundraiser.strategy.button.tactics" action="/member/tactic/list?strategyId=${id}"/>
    <acme:button code="any.strategy.form.label.fundraiser" action="/any/fundraiser/show?id=${fundraiserId}"/>


    <jstl:if test="${projectId != null}">
		<acme:submit code="member.strategy.button.unassign" action="/fundraiser/strategy/unassign?strategyId=${id}"/>
	</jstl:if>    	
        	
   
</acme:form>