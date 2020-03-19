package fr.insee.tp.dao;

import java.util.List;

import fr.insee.tp.domaine.Maladie;

public interface IMaladieDAO extends GenericDAO<Maladie, Integer> {
	List<Maladie> getListeMaladieParNom(String nomMaladie);

	long getNombreMaladieParNom(String nom);

	List<Maladie> getListeMaladieParNomEnLecture(String string);
}
