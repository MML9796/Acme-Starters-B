<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.manager.form.label.username" path="userAccount.username"/>
	<acme:form-textbox code="any.manager.form.label.position" path="position"/>
	<acme:form-textarea code="any.manager.form.label.someSkills" path="someSkills"/>
	<acme:form-checkbox code="any.manager.form.label.isExecutive" path="isExecutive"/>
</acme:form>