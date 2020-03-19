package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.domaine.DossierMedical;

public class DossierMedicalDAO extends AbstractGenericDAO<DossierMedical, Integer> implements GenericDAO<DossierMedical, Integer> {

	public List<DossierMedical> getDossiersByDescription(String description) {
		String hql = "select distinct(dm) from DossierMedical dm join dm.maladies m where m.description=:desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("desc", description);
		return q.list();
	}

	public List<DossierMedical> getDossiersByDescriptionCriteria(String description) {
		Criteria c = getSession().createCriteria(DossierMedical.class, "dm");
		c.setFetchMode("dm.maladies", FetchMode.JOIN);
		c.createAlias("maladies", "m");
		// c.add(Restrictions.eq("m.description", description));

		return c.list();

	}

}
