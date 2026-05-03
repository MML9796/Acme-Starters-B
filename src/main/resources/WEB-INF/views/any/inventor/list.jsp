<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.inventor.list.label.username" path="userAccount.username"/>
	<acme:list-column code="any.inventor.list.label.bio" path="bio"/>
	<acme:list-hidden path="keyWords"/>
	<acme:list-hidden path="licensed"/>
</acme:list>