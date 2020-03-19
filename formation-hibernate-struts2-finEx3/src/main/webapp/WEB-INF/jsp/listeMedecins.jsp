<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/formation.css">
<title>Liste des médecins</title>
</head>
<body>
<h1>Liste des médecins</h1>
<p>Nous sommes le <s:date name="date" format="dd/MM/yyyy" />.</p>
<table class="table table-striped table-condensed table-hover">
	<caption>Liste des médecins</caption>
	<thead>
		<tr>
			<th>Id</th>
			<th>Prénom</th>
			<th>Nom</th>
			<th>Téléphone</th>
			<th>Secteur</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="medecins" var="medecin">
		<s:url namespace="/medecin" action="listeConsultations" var="urlVoirConsultations" >
		    <s:param name="medecin">
				<s:property value="#medecin"/>
			</s:param>
		</s:url>
		<tr>	
			<td><s:a href="%{urlVoirConsultations}"><s:property value="#medecin.id" /></s:a>
			</td>
			<td><s:property value="#medecin.prenom" /></td>
			<td><s:property value="#medecin.nom" /></td>
			<td><s:property value="#medecin.telephone" /></td>
			<td>
			<s:if test="#medecin.secteur == 3">
				<strong><s:property value="#medecin.secteur" /></strong>
			</s:if>
			<s:elseif test="#medecin.secteur == 2">
				<em><s:property value="#medecin.secteur" /></em>
			</s:elseif>
			<s:else>
				<s:property value="#medecin.secteur" />
			</s:else>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
<a href="<%=request.getContextPath()%>/accueil">Retour à l'accueil</a>
</body>
</html>