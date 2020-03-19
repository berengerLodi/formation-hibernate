package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Query;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.domaine.PbId;

public class PbIdDAO extends AbstractGenericDAO<PbId, Integer> implements
		GenericDAO<PbId, Integer> {

	public List<PbId> getPbId() {
		String hql = "SELECT p FROM PbId p";

		Query q = getSession().createQuery(hql);
		return q.list();

	}
}
