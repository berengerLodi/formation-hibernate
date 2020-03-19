package fr.insee.tp.dao.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.Acte;

public class ActeDAOTest {
	@Ignore
	@Test
	public void insert() {
		HibernateUtils.open();
		GenericDAO<Acte, Integer> acteDAO = new ActeDAO();

		Acte acte = new Acte();
		acte.setNom("Massage");
		acte.setPrix(200F);
		acte.setRemboursable(true);
		acteDAO.insert(acte);

		Assert.assertTrue(acte.getId() != null);
		HibernateUtils.close();
	}

}
