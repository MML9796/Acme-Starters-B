<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="manager.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="manager.campaign.form.label.description" path="description"/>
	<acme:form-moment code="manager.campaign.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.campaign.form.label.endMoment" path="endMoment"/>
	<acme:form-moment code="manager.campaign.form.label.monthsActive" path="monthsActive"/>
	<acme:form-double code="manager.campaign.form.label.effort" path="effort"/>
	<acme:form-textbox code="manager.campaign.form.label.moreInfo" path="moreInfo"/>
	<acme:button code="manager.campaign.button.milestone" action="/manager/milestone/list?campaignId=${id}"/>
</acme:form>