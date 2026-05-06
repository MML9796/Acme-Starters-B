<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>

    <acme:list-column code="manager.projects.list.label.title" path="title"/>
    <acme:list-column code="manager.projects.list.label.keyWords" path="keyWords"/>
    <acme:list-column code="manager.projects.list.label.kickOffMoment" path="kickOffMoment"/>
    <acme:list-column code="manager.projects.list.label.closeOutMoment" path="closeOutMoment"/>
    <acme:list-hidden path="description"/>
</acme:list>

<acme:button code="manager.projects.button.create" action="/manager/project/create"/>