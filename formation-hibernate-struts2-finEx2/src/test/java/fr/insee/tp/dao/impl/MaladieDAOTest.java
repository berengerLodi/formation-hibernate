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
import fr.insee.tp.domaine.Maladie;

public class MaladieDAOTest {

	@Ignore
	@Test
	public void testLectureEtSuppressionSansDAO() throws Exception {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Maladie maladie = (Maladie) session.get(Maladie.class, 1);
		Assert.assertTrue(maladie != null);
		session.delete(maladie);
		System.out.println("après delete"
			+ (session.get(Maladie.class, 1)));
		session.flush();
		System.out.println("après flush :"
			+ (session.get(Maladie.class, 1)));
		Maladie maladie2 = (Maladie) session.get(Maladie.class, 1);
		Assert.assertTrue(maladie2 == null);

		transaction.commit();
	}

	@Ignore
	@Test
	public void testLectureEtSuppressionAvecDAO() throws Exception {

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
	public void testTrouverMaladieParDescriptionDAO() {

		HibernateUtils.open();
		MaladieDAO maladieDAO = new MaladieDAO();

		List<Maladie> maladies = maladieDAO.trouverParDescription("Inconnue");
		Assert.assertTrue(maladies.size() > 0);

		Maladie m = maladieDAO.getById(84);
		HibernateUtils.close();

		for (Maladie maladie : maladies) {
			System.out.println("l'objet java vaut " + maladie);
			maladie.setDescription("gros gros rhume");
		}
	}

}
