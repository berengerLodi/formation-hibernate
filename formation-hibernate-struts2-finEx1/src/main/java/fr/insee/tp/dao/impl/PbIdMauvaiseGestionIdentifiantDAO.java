package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Query;

import fr.insee.tp.dao.AbstractGenericDAO;
import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.domaine.PbId;
import fr.insee.tp.domaine.PbIdMauvaiseGestionDesId;

public class PbIdMauvaiseGestionIdentifiantDAO extends AbstractGenericDAO<PbIdMauvaiseGestionDesId, Integer> implements
		GenericDAO<PbIdMauvaiseGestionDesId, Integer> {

	public List<PbIdMauvaiseGestionDesId> getPbId() {
		String hql = "SELECT p FROM PbIdMauvaiseGestionDesId p";

		Query q = getSession().createQuery(hql);
		return q.list();

	}
}
