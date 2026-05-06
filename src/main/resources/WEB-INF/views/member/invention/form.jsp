<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="inventor.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="inventor.invention.form.label.name" path="name"/>
	<acme:form-textarea code="inventor.invention.form.label.description" path="description"/>
	<acme:form-moment code="inventor.invention.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="inventor.invention.form.label.endMoment" path="endMoment"/>
	<acme:form-url code="inventor.invention.form.label.moreInfo" path="moreInfo"/>

    <acme:form-double code="inventor.invention.form.label.monthsActive" path="monthsActive" readonly="true"/>
    <acme:form-double code="inventor.invention.form.label.cost" path="cost" readonly="true"/>
         
    <acme:button code="inventor.invention.button.parts" action="/member/part/list?inventionId=${id}"/>
	<acme:button code="any.invention.button.inventor" action="/any/inventor/show?id=${inventorId}"/>
    

	<jstl:if test="${projectId != null}">
		<acme:submit code="member.invention.button.unassign" action="/inventor/invention/unassign?inventionId=${id}"/>
	</jstl:if>
        
</acme:form>