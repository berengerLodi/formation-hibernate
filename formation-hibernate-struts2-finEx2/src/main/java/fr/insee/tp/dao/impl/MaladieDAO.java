package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.domaine.Maladie;

public class MaladieDAO extends AbstractGenericDAO<Maladie, Integer> implements GenericDAO<Maladie, Integer> {

	public List<Maladie> trouverParDescription(String description) {
		String hql = "select m from Maladie m where m.description=:desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("desc", description);
		return query.list();
	}

	public List<Maladie> trouverParDescriptionCriteria(String description) {
		Criteria c = getSession().createCriteria(Maladie.class);
		c.add(Restrictions.eq("description", description));
		return c.list();
	}

}
