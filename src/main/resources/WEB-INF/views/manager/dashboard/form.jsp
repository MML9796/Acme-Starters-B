<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:print code="manager.dashboard.form.title.project"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.number-projects"/>
		</th>
		<td>
			<acme:print value="${numberOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.deviation-number-projects-other-managers"/>
		</th>
		<td>
			<acme:print value="${deviationOfNumberOfProjectsWrtAvgOfOtherManagers}"/>
		</td>
	</tr>	
</table>

<h2>
	<acme:print code="manager.dashboard.form.title.effort"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.mimimum-effort-projects"/>
		</th>
		<td>
			<acme:print value="${mimimumEffortOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.maximum-effort-projects"/>
		</th>
		<td>
			<acme:print value="${maximumEffortOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.average-effort-projects"/>
		</th>
		<td>
			<acme:print value="${averageEffortOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.average-absoulte-deviation-effort-projects"/>
		</th>
		<td>
			<acme:print value="${averageAbsoulteDeviationOfEffortOfProjects}"/>
		</td>
	</tr>
</table>
