<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.util.Util" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css"
		rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<title>Liste des clients</title>
</head>
<body>

	<!-- Créer un lien pour la colonne prénom -->
	
	<c:url var="lienTriPrenom" value="/client/liste">
					<c:param name="tri" value="<%= Integer.toString(Util.PRENOM) %>" />
				</c:url>
				
				<!-- Créer un lien pour les colonnes nom et email -->
				
			
				<c:url var="lienTriNom" value="/client/liste">
					<c:param name="tri" value="<%= Integer.toString(Util.NOM) %>" />
				</c:url>					
 
				
				<c:url var="lienTriEmail" value="/client/liste">
					<c:param name="tri" value="<%= Integer.toString(Util.EMAIL) %>" />
				</c:url>

	<div id="wrapper">

		<div id="header">

			<div id="content">
			
			<!-- ajouter un nouveau bouton pour Ajouter les Clients -->
			
			<input type="button" value="Ajouter client"
			onclick="window.location.href='afficherFormulaireAjout'; return false; "
			class="add-button"
			/>
			
			<!--  add a search box -->
			<form:form action="recherche" method="GET">
				Rechercher client: <input type="text" name="nomCherche" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<!--  Ajouter le tableau HTML -->
				<table>
					<tr>
						<th>
						<a href="${lienTriPrenom}"> Prénom </a></th>
						<th>
						<a href="${lienTriNom}"> Nom</a> </th>
						<th>
						<a href="${lienTriEmail}"> Email</a> </th>
						<th>Action</th>
					</tr>

					<c:forEach var="clientTemp" items="${clients}">
					
					<!-- Construire un lien pour la màj -->
					<c:url var="majClient" value="/client/afficherFormulaireMaj">
					<c:param name="clientId" value="${clientTemp.id }">
					</c:param>
					</c:url>
					
					<!-- Construire un lien pour la suppression -->
					<c:url var="suppressionClient" value="/client/supprimerClient">
					<c:param name="clientId" value="${clientTemp.id }">
					</c:param>
					</c:url>

						<tr>
							<td>${clientTemp.prenom }</td>
							<td>${clientTemp.nom }</td>
							<td>${clientTemp.email}</td>
							
							<!-- afficher le lien de màj -->
							<td>
							<a href="${majClient}">Mise à jour</a>
							|
							<a href="${suppressionClient}"
							onclick="if (!(confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?'))) return false" >Supprimer</a>
							</td>
						</tr>

					</c:forEach>

				</table>
			</div>

		</div>
	</div>

	Bientôt en ligne !
</body>
</html>