package fr.insee.tp.fonctionnementHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.insee.tp.domaine.Medecin;

public class MedecinLanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
		System.out.println(idMedecin);

		transaction.commit();
	}

}
