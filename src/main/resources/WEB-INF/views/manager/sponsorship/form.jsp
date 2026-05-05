<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.sponsorship.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="manager.sponsorship.form.label.name" path="name"/>
	<acme:form-textarea code="manager.sponsorship.form.label.description" path="description"/>
	<acme:form-moment code="manager.sponsorship.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.sponsorship.form.label.endMoment" path="endMoment"/>
	<acme:form-url code="manager.sponsorship.form.label.moreInfo" path="moreInfo"/>
	<acme:form-double code="manager.sponsorship.form.label.monthsActive" path="monthsActive"/>
	<acme:form-double code="manager.sponsorship.form.label.totalMoney" path="totalMoney"/>
	<acme:button code="manager.sponsorship.button.donation" action="/manager/donation/list?sponsorshipId=${id}"/>
    <acme:button code="manager.sponsorship.button.sponsor" action="/manager/sponsor/show?sponsorshipId=${id}"/>
</acme:form>