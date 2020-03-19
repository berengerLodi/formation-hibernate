package fr.insee.tp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.dao.IMaladieDAO;
import fr.insee.tp.domaine.Maladie;

public class MaladieDAOTest {
	/** Test mis en @Ignore car agit sur le contenu de la base */
	@Ignore
	@Test
	public void testLectureViaGetEtSuppressionSansDAO() throws Exception {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Maladie maladie = (Maladie) session.get(Maladie.class, 1);
		Assert.assertTrue(maladie != null);
		session.delete(maladie);
		System.out.println("après delete"
				+ ((Maladie) session.get(Maladie.class, 1)));
		session.flush();
		System.out.println("après flush :"
				+ ((Maladie) session.get(Maladie.class, 1)));
		Maladie maladie2 = (Maladie) session.get(Maladie.class, 1);
		Assert.assertTrue(maladie2 == null);

		transaction.commit();
	}

	/** Test mis en @Ignore car agit sur le contenu de la base */
	@Ignore
	@Test
	public void testLectureViaGetEtSuppressionAvecDAO() throws Exception {

		HibernateUtils.open();
		GenericDAO<Maladie, Integer> maladieDAO = new MaladieDAO();

		Maladie maladie = maladieDAO.getById(2);
		Assert.assertTrue(maladie != null);
		maladieDAO.delete(maladie);
		Assert.assertTrue(maladie != null);
		System.out.println("après delete, l'objet java vaut " + maladie);
		Maladie maladie2 = maladieDAO.getById(2);
		System.out.println("celui sensément récupéré depuis la BDD vaut : "
				+ maladie2);
		Assert.assertTrue(maladie2 == null);

		HibernateUtils.close();
	}

	@Test
	public void testRequeteHQLPamametree() throws Exception {

		HibernateUtils.open();

		IMaladieDAO maladieDAO = new MaladieDAO();
		List<Maladie> maladies = maladieDAO.getListeMaladieParNom("Inconnue");
		Assert.assertEquals(2, maladies.size());

		HibernateUtils.close();
	}

	/**
	 * test "en lecture seule" en faisant appel au HQL via new. On verifie qu'en
	 * base, les maladies restent bien avec une description " Inconnue"!
	 */
	@Test
	public void testRequeteHQLPamametreeLecture() throws Exception {

		HibernateUtils.open();

		IMaladieDAO maladieDAO = new MaladieDAO();
		List<Maladie> maladies = maladieDAO
				.getListeMaladieParNomEnLecture("Inconnue");
		Assert.assertEquals(2, maladies.size());
		for (Maladie maladie : maladies) {
			maladie.setDescription("sommatique");
		}
		HibernateUtils.close();
		testRequeteHQLPamametreeCount();

	}

	@Test
	public void testRequeteHQLPamametreeCount() throws Exception {

		HibernateUtils.open();

		IMaladieDAO maladieDAO = new MaladieDAO();
		long nbMaladie = maladieDAO.getNombreMaladieParNom("Inconnue");
		Assert.assertEquals(2, nbMaladie);
		HibernateUtils.close();
	}

}
