<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/formation.css">
<title>Consultation</title>
</head>
<body>
<h1>Consultation</h1>
<h2>Consultation</h2>
<p>Medecin : <s:property value="consultation.medecin.prenom"/> <s:property value="consultation.medecin.nom"/></p>
<p>Patient : <s:property value="consultation.patient.prenom" /> <s:property value="consultation.patient.nom" />
<p>Date : <s:date name="consultation.date" format="dd/MM/yyyy"/>
<h2>Lieu de la consultation</h2>
<p>Ville : <s:property value="consultation.lieu.ville"/></p>
<p>Voie : <s:property value="consultation.lieu.voie"/></p>
<a href="<%=request.getContextPath()%>/accueil">Retour à l'accueil</a>
</body>
</html>