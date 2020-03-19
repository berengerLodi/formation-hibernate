<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/formation.css">
<title>Liste des consultations</title>
</head>
<body>
<h1>Liste des consultations</h1>
<table class="table table-striped table-condensed table-hover">
	<caption>Liste des consultations du docteur <s:property value="medecin.prenom" /> <s:property value="medecin.nom" /></caption>
	<thead>
		<tr>
			<th>Id</th>
			<th>Date</th>
			<th>Patient</th>
			<th>Ville</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="consultations" var="consultation">	
		<tr>	
			<td><s:property value="#consultation.id" /></td>
			<td><s:date name="#consultation.date" format="dd/MM/yyyy" /></td>
			<td><s:property value="#consultation.patient.prenom" /> <s:property value="#consultation.patient.nom" /></td>
			<td><s:property value="#consultation.lieu.ville" /></td>
		</tr>
		</s:iterator>
	</tbody>
</table>
<p><a href="<%=request.getContextPath()%>/medecin/listeMedecins">Liste des médecins</a></p>
<s:url namespace="/medecin" action="ajouterConsultation" var="urlAjouterConsultations" >
    <s:param name="medecin">
		<s:property value="medecin"/>
	</s:param>
</s:url>
<p><s:a href="%{urlAjouterConsultations}">Ajouter une consultation</s:a></p>
</body>
</html>