package fr.insee.tp.service;

import java.util.List;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Soin;

public interface ConsultationService extends DomaineService<Consultation>{
	
	/**
	 * <p>La liste des soins ({@link Soin}) associé à toutes les ordonnances rédigée par le médecin à l'issue d'une {@link Consultation} donnée.
	 * <p>Les soins peuvent se retrouver en double et ne sont pas particlièrement triés.
	 * @return La liste de tous les soins associés à la consultation <code>consultation</code>, ou une liste vide s'il n'y en a aucun.
	 * */
	List<? extends Soin> soins(Consultation consultation);
}
