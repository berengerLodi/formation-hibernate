package fr.insee.tp.service;

import java.util.Date;
import java.util.SortedMap;
import java.util.SortedSet;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;
import fr.insee.tp.domaine.Soin;

public interface MedecinService extends DomaineService<Medecin>{

	/**
	 * La liste naturellement triée de tous les patients ({@link Patient}) qu'un {@link Medecin} a déjà eu en consultation.
	 * @return La liste des patients que le <code>medecin</code> a eu en consultation, ou une liste vide s'il n'a jamais donné de consultation.
	 * */
	SortedSet<Patient> patients(Medecin medecin);
	
	/**
	 * La liste de toutes les consultations ({@link Consultation}) d'un {@link Medecin}.
	 * @return La liste de toutes les consultations du <code>medecin</code>, ou une liste vide s'il n'a jamais donné de consultation.
	 * */
	SortedSet<Consultation> consultations(Medecin medecin);
	
	/**
	 * Les consultations données par le médecin entre deux dates.
	 * @return La liste de toutes les consultations du <code>medecin</code>, entre <code>debut</code> et <code>fin</code>, ou une liste vide s'il n'y en a pas eu entre ces deux dates.
	 * */
	SortedSet<Consultation> consultations(Medecin medecin, Date debut, Date fin);
	
	/**
	 * <p>La liste de tous les soins prescrits par un medecin, chacun associé à la quantité totale prescrite.
	 * Exemple :
	 * <code><p>
	 * [Soin("doliprane") -> 35,<br/>
	 * Soin("scanner") -> 3,<br/>
	 * Soin("infirmier à domicile") -> 12]
	 * </p></code>
	 * */
	SortedMap<Soin, Integer> frequenceSoins(Medecin medecin);
}
