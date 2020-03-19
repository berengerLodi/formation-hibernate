package fr.insee.tp.fonctionnementHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.insee.tp.domaine.Acte;

public class ActeLanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Acte acte = new Acte();
		acte.setNom("Massage");
		acte.setPrix(200F);
		acte.setRemboursable(true);
		session.persist(acte);

		System.out.println("Objet persisté : " + acte.toString());
		session.save(acte);
		System.out.println("Objet persistant en base :" + acte.toString());

		transaction.commit();

	}

}
