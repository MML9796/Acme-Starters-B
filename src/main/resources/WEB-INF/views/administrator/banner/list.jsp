<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" width="30%"/>
	<acme:list-column code="administrator.banner.list.label.targetUrl" path="targetUrl" width="70%"/>
	<acme:list-hidden path="picture"/>
</acme:list>

<acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>