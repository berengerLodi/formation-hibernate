package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.IMedecinDAO;
import fr.insee.tp.domaine.Medecin;

public class MedecinDAO extends AbstractGenericDAO<Medecin, Integer> implements
		IMedecinDAO {

	public List<Medecin> getListeMedecinParSecteur(int numeroSecteur) {
		Criteria crit = this.getSession().createCriteria(Medecin.class)
				.add(Restrictions.eq("secteur", numeroSecteur));
		List<Medecin> retour = crit.list();
		return retour;
	}
}
