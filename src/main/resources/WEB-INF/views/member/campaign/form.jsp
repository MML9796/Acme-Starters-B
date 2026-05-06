<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="spokesperson.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="spokesperson.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="spokesperson.campaign.form.label.description" path="description"/>
	<acme:form-moment code="spokesperson.campaign.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="spokesperson.campaign.form.label.endMoment" path="endMoment"/>
	<acme:form-url code="spokesperson.campaign.form.label.moreInfo" path="moreInfo"/>
	
    <acme:form-double code="spokesperson.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
    <acme:form-double code="spokesperson.campaign.form.label.effort" path="effort" readonly="true"/>
       
    <acme:button code="spokesperson.campaign.button.milestone" action="/member/milestone/list?campaignId=${id}"/>
	<acme:button code="any.campaign.button.spokesperson" action="/any/spokesperson/show?id=${spokespersonId}"/>
	

     <jstl:if test="${projectId != null}">
		<acme:submit code="member.campaign.button.unassign" action="/spokesperson/campaign/unassign?campaignId=${id}"/>
	 </jstl:if>



</acme:form>