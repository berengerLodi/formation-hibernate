package fr.insee.tp.dao.impl;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.DossierMedical;

public class DossierMedicalDAOTest {

	@Ignore
	@Test
	public void testGetByDesc() throws Exception {

		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();
		List<DossierMedical> dossiers = dmDAO.getDossiersByDescription("Malaria");

		for (DossierMedical dossierMedical : dossiers) {
			System.out.println(dossierMedical);
		}

		HibernateUtils.close();

		for (DossierMedical dossierMedical : dossiers) {
			dossierMedical.getMaladies().size();
		}

	}

	@Test
	public void testGetByDescCriteria() throws Exception {

		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();
		List<DossierMedical> dossiers = dmDAO.getDossiersByDescriptionCriteria("Malaria");

		// for (DossierMedical dossierMedical : dossiers) {
		// System.out.println(dossierMedical);
		// }

		HibernateUtils.close();

		for (DossierMedical dossierMedical : dossiers) {
			dossierMedical.getMaladies().size();
		}

	}

}
