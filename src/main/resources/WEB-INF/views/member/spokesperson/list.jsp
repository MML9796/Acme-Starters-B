<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.spokesperson.list.label.username" path="userAccount.username"/>
	<acme:list-column code="any.spokesperson.list.label.cv" path="cv"/>
	<acme:list-hidden path="achievements"/>
	<acme:list-hidden path="licensed"/>
</acme:list>