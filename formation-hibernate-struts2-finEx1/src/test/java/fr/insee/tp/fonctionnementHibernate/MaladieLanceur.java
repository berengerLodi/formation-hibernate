package fr.insee.tp.fonctionnementHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.insee.tp.domaine.Maladie;

public class MaladieLanceur {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Maladie maladie = (Maladie) session.get(Maladie.class, 1);
		System.out.println("recuperation objet :" + maladie.toString());

		session.delete(maladie);
		System.out.println("après delete"
				+ ((Maladie) session.get(Maladie.class, 1)));
		session.flush();
		System.out.println("après flush :"
				+ ((Maladie) session.get(Maladie.class, 1)));

		transaction.commit();

	}

}
