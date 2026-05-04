<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="manager.invention.form.label.name" path="name"/>
	<acme:form-textarea code="manager.invention.form.label.description" path="description"/>
	<acme:form-moment code="manager.invention.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.invention.form.label.endMoment" path="endMoment"/>
	<acme:form-double code="manager.invention.form.label.monthsActive" path="monthsActive"/>
	<acme:form-money code="manager.invention.form.label.cost" path="cost"/>
	<acme:form-url code="manager.invention.form.label.moreInfo" path="moreInfo"/>
	<acme:button code="manager.invention.button.parts" action="/manager/part/list?inventionId=${id}"/>
</acme:form>