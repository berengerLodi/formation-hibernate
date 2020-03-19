package fr.insee.tp.dao;

import java.util.List;

import fr.insee.tp.domaine.Medecin;

public interface IMedecinDAO extends GenericDAO<Medecin, Integer> {
	public List<Medecin> getListeMedecinParSecteur(int numeroSecteur);
}
