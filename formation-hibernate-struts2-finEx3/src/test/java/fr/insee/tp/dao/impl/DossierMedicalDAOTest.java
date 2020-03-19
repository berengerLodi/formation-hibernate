package fr.insee.tp.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.LazyInitializationException;
import org.junit.Test;

import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.DossierMedical;
import fr.insee.tp.domaine.Maladie;

public class DossierMedicalDAOTest {

	/*
	 * Sans indications supplementaires, le lien entre DossierMedical et Maladie
	 * est en mode lazy : il y a une premiere requête au niveau du DAO, puis une
	 * seconde, juste après "nombre de dossiers" pour rechercher les maladies en
	 * base. en ajoutant @OneToMany(fetch = FetchType.EAGER), il n'y a plus
	 * qu'une requête
	 */
	@Test
	public void testFetchsModes() throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		DossierMedical dm = dmDAO.getById(2);

		System.out.println("****************");

		// l'instruction suivante génère une requête supplémentaire
		for (Maladie maladie : dm.getMaladies()) {
			System.out.println(maladie);
		}
		HibernateUtils.close();
	}

	/**
	 * Test d'utilisation de HQL . jointure simple. Attention, présence de
	 * doublons
	 */
	@Test
	public void testGetDMDePatientsEnFonctionDeLeursMaladiesViaHqlSansExceptionNiDistinct()
			throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO
				.getDMEnFonctionDeLeursMaladiesViaHql("Malaria");
		Assert.assertEquals(2, listeDm.size());
		for (DossierMedical dossierMedical : listeDm) {
			System.out.println(dossierMedical);
		}
		HibernateUtils.close();
		System.out.println("****************");

	}

	/**
	 * Test d'utilisation de HQL . La requête fonctionne, mais la liste des
	 * maladies n'est pas chargée, la partie for ne doit pas être executée!
	 */
	@Test
	public void testGetDMDePatientsEnFonctionDeLeursMaladiesViaHqlSansExceptionavecDistinct()
			throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO
				.getDMEnFonctionDeLeursMaladiesViaHqlDistinct("Malaria");
		Assert.assertEquals(1, listeDm.size());
		for (DossierMedical dossierMedical : listeDm) {
			System.out.println(dossierMedical);
		}
		HibernateUtils.close();
		System.out.println("****************");

	}

	/**
	 * Test d'utilisation de HQL . La requête fonctionne, mais la liste des
	 * maladies n'est pas chargée, la partie for ne doit pas être executée!
	 */
	@Test(expected = LazyInitializationException.class)
	public void testGetDMDePatientsEnFonctionDeLeursMaladiesViaHql()
			throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO
				.getDMEnFonctionDeLeursMaladiesViaHqlDistinct("Malaria");
		Assert.assertEquals(1, listeDm.size());

		HibernateUtils.close();
		System.out.println("****************");
		for (DossierMedical dossierMedical : listeDm) {
			System.out.println(dossierMedical);
		}
		fail();

	}

	/**
	 * Test d'utilisation de HQL et fetch. Remarquer que par fetch, on ne
	 * recupère plus le dossier medical et l'ensemble des maladies, mais le
	 * dossiers medical et uniquement les malaria
	 */
	@Test
	public void testGetDMDePatientsEnFonctionDeLeursMaladiesViaHqlDistinctEtFetch()
			throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO
				.getDMEnFonctionDeLeursMaladiesViaHqlDistinctEtFetch("Malaria");
		Assert.assertEquals(1, listeDm.size());
		HibernateUtils.close();

		System.out.println("****************");
		for (DossierMedical dossierMedical : listeDm) {
			System.out.println(dossierMedical);
		}

	}

	/*
	 * Sans indications supplementaires, le lien entre DossierMedical et Maladie
	 * est en mode lazy : il y a une premiere requête au niveau du DAO,, puis
	 * une seconde, juste après "nombre de dossiers" pour rechercher les
	 * maladies en base. en ajoutant @OneToMany(fetch = FetchType.EAGER), il n'y
	 * a plus qu'une requête. Autre possibilité : le parametrer lors de la
	 * requête Criteria.
	 */
	@Test
	public void testGetListeDossierMedicalViaCriteria() throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO.getListDossierMedical();

		System.out.println("****************");
		System.out.println("nombre de dossiers médicaux : " + listeDm.size());

		DossierMedical dossierMedical1 = listeDm.get(1);
		System.out.println("****************");
		for (Maladie maladie : dossierMedical1.getMaladies()) {
			System.out.println(maladie);
		}
		HibernateUtils.close();
	}

	/** Test d'utilisation de Criteria */
	@Test
	public void testGetListeDossierMedicalParNom() throws Exception {
		HibernateUtils.open();
		DossierMedicalDAO dmDAO = new DossierMedicalDAO();

		System.out.println("****************");
		List<DossierMedical> listeDm = dmDAO
				.getDMDePatientsEnFonctionDeLeurNom("CAMUS");

		System.out.println("****************");
		System.out.println("nombre de dossiers médicaux : " + listeDm.size());
		System.out.println(listeDm);
		Assert.assertEquals(1, listeDm.size());

		HibernateUtils.close();
	}

}
