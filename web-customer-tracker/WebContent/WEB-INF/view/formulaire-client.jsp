<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
<title>Formulaire client</title>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<form:form action="enregistrerClient" modelAttribute="client"
			method="POST">
			
			<!-- Il faut associer les données obtenues avec l'id du CLient -->
			<form:hidden path="id"/>

			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="prenom" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="nom" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>
		
		<p>
			<a href="${pageContext.request.contextPath}/client/liste">Retour à la liste</a>
		</p>

	</div>
</body>
</html>