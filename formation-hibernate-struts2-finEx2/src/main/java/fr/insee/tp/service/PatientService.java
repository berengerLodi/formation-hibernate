package fr.insee.tp.service;

import java.util.SortedSet;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Maladie;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;

public interface PatientService extends DomaineService<Patient>{

	/**
	 * La liste naturellement triée de tous les médecins ({@link Medecin}) qu'un {@link Patient} a déjà consulté.
	 * @return La liste des médecins que le <code>patient</code> a déjà consulté, ou une liste vide s'il n'a jamais consulté.
	 * */
	SortedSet<Medecin> medecins(Patient patient);
	
	/**
	 * La liste de toutes les consultations d'un {@link Patient}.
	 * @return La liste de toutes les consultations du <code>patient</code>, ou une liste vide s'il n'a jamais consulté.
	 * */
	SortedSet<Consultation> consultations(Patient patient);
	
	/**
	 * Retourne la maladie la plus récente contractée par le patient, ou <code>null</code> s'il n'en n'a jamais eu.
	 * */
	Maladie derniereMaladie(Patient patient);
}
