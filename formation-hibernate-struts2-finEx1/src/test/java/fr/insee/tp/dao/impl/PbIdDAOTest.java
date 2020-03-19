package fr.insee.tp.dao.impl;

import java.util.List;

import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.PbId;
import fr.insee.tp.domaine.PbIdMauvaiseGestionDesId;

public class PbIdDAOTest {

	@Test
	public void testKo() throws Exception {
		HibernateUtils.open();
		GenericDAO<PbIdMauvaiseGestionDesId, Integer> dao = new PbIdMauvaiseGestionIdentifiantDAO();
		List<PbIdMauvaiseGestionDesId> listePbId = dao.findAll();
		System.out.println(listePbId);
		HibernateUtils.close();
	}

	@Test
	public void testOk() throws Exception {
		HibernateUtils.open();
		GenericDAO<PbId, Integer> dao = new PbIdDAO();
		List<PbId> listePbId = dao.findAll();
		System.out.println(listePbId);
		HibernateUtils.close();
	}

}
