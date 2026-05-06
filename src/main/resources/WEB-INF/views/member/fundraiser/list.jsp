<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.fundraiser.list.label.username" path="userAccount.username"/>
	<acme:list-column code="any.fundraiser.list.label.bank" path="bank"/>
	<acme:list-hidden path="statement"/>
	<acme:list-hidden path="agent"/>
</acme:list>