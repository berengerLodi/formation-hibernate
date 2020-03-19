<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/formation.css">
<s:head/>
<title>Ajouter une consultation</title>
</head>
<body>
<h1>Ajouter une consultation</h1>
<s:form namespace="/medecin" action="enregistrerConsultation" validate="false">
	<s:actionerror cssClass="text-error unstyled" />
	<fieldset>
		<legend>Consultation</legend>
		<p>Medecin : <s:property value="medecin.prenom"/> <s:property value="medecin.nom"/></p>
		<input type="hidden" name="consultation.medecin" value="<s:property value="medecin"/>" >
		<p>
			Patient : <s:select list="patients" name="consultation.patient" listKey="id" listValue="nom"/>
			<s:fielderror fieldName="consultation.patient" cssClass="text-error unstyled"/>
		</p>
		
		<p>
			Date : <s:textfield name="consultation.date"/>
			<s:fielderror fieldName="consultation.date" cssClass="text-error unstyled"/>
		</p>
	</fieldset>
	<fieldset>
		<legend>Lieu de la consultation</legend>
		<p>
			Ville : <s:textfield name="consultation.lieu.ville"/>
			<s:fielderror fieldName="consultation.lieu.ville" cssClass="text-error unstyled" />
		</p>
		<p>
			Voie : <s:textfield name="consultation.lieu.voie"/>
			<s:fielderror fieldName="consultation.lieu.voie" cssClass="text-error unstyled" />
		</p>
	</fieldset>
	<button type="submit" class="btn btn-primary">Enregistrer consultation</button>
</s:form>
<a href="<%=request.getContextPath()%>/accueil">Retour à l'accueil</a>
</body>
</html>