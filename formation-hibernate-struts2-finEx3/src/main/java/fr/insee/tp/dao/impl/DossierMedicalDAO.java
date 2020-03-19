package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.domaine.DossierMedical;

public class DossierMedicalDAO extends
		AbstractGenericDAO<DossierMedical, Integer> implements
		GenericDAO<DossierMedical, Integer> {

	public List<DossierMedical> getDMEnFonctionDeLeursMaladiesViaHql(
			String nomMaladie) {
		String requete = "select dossierMed from DossierMedical dossierMed join dossierMed.maladies maladie where maladie.description = :varNomMaladie ";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);

		return query.list();
	}

	public List<DossierMedical> getDMEnFonctionDeLeursMaladiesViaHqlDistinct(
			String nomMaladie) {
		String requete = "select distinct dossierMed from DossierMedical dossierMed join dossierMed.maladies maladie where maladie.description = :varNomMaladie ";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);

		return query.list();
	}

	public List<DossierMedical> getDMEnFonctionDeLeursMaladiesViaHqlDistinctEtFetch(
			String nomMaladie) {
		String requete = "select distinct dossierMed from DossierMedical dossierMed join fetch dossierMed.maladies maladie where maladie.description = :varNomMaladie ";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<DossierMedical> getListDossierMedical() {
		Criteria crit = this.getSession().createCriteria(DossierMedical.class);
		List<DossierMedical> retour = crit
		// .setFetchMode("maladies",FetchMode.JOIN)
				.list();
		return retour;
	}

	@SuppressWarnings("unchecked")
	public List<DossierMedical> getDMDePatientsEnFonctionDeLeurNom(String nom) {
		Criteria crit = this.getSession().createCriteria(DossierMedical.class);
		List<DossierMedical> retour = crit.createCriteria("patient")
				.add(Restrictions.eq("nom", nom)).list();
		return retour;
	}

}
