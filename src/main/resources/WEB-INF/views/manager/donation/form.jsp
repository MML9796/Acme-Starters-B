<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.donation.form.label.name" path="name"/>
	<acme:form-textarea code="manager.donation.form.label.notes" path="notes"/>
	<acme:form-money code="manager.donation.form.label.money" path="money"/>
	<acme:form-select code="manager.donation.form.label.kind" path="kind" choices="${listaKinds}"/>
</acme:form>