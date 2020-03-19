package fr.insee.tp.dao.impl;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Ignore;
import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.Medecin;

public class MedecinDAOTest {

	@Ignore
	@Test
	public void testInsertSansDAO() throws Exception {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Medecin medecin = new Medecin();
		medecin.setNom("Dupont");
		medecin.setPrenom("Jean");
		medecin.setTelephone("0101010101");
		medecin.setSecteur(2);

		session.save(medecin);

		int idMedecin = medecin.getId();
		Assert.assertTrue(idMedecin != 0);
		System.out.println("après le save et avant le flush, idMedecin : "
				+ idMedecin);
		session.flush();
		System.out.println("après le flush, avant le close");
		transaction.commit();
	}

	@Ignore
	@Test
	public void testInsertAvecDAO() throws Exception {

		HibernateUtils.open();
		GenericDAO<Medecin, Integer> medecinDAO = new MedecinDAO();

		Medecin medecin = new Medecin();
		medecin.setNom("No");
		medecin.setPrenom("JeanBon");
		medecin.setTelephone("0123456789");
		medecin.setSecteur(2);
		System.out.println("***avant insertOrUpdate du DAO**");
		medecinDAO.insertOrUpdate(medecin);

		int idMedecin = medecin.getId();
		System.out
				.println("après le save et avant le HibernateUtils.close(), idMedecin : "
						+ idMedecin);
		HibernateUtils.close();
	}

}
