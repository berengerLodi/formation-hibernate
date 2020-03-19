package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Query;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.IMaladieDAO;
import fr.insee.tp.domaine.Maladie;

public class MaladieDAO extends AbstractGenericDAO<Maladie, Integer> implements
		IMaladieDAO {

	@Override
	public List<Maladie> getListeMaladieParNom(String nomMaladie) {
		String requete = "select maladie from Maladie maladie where maladie.description= :varNomMaladie";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);
		return query.list();

	}

	public long getNombreMaladieParNom(String nomMaladie) {
		String requete = "select count(maladie) from Maladie maladie where maladie.description= :varNomMaladie";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);
		long retour = (Long) query.uniqueResult();
		return retour;

	}

	@Override
	public List<Maladie> getListeMaladieParNomEnLecture(String nomMaladie) {
		String requete = "select new Maladie(maladie.id, maladie.description, maladie.date) from Maladie maladie where maladie.description= :varNomMaladie";
		Query query = this.getSession().createQuery(requete);
		query.setParameter("varNomMaladie", nomMaladie);
		return query.list();

	}

}
