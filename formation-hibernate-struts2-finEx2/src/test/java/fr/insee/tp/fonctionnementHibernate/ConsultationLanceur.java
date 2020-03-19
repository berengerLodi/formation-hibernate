package fr.insee.tp.fonctionnementHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;

/**
 * Cette classe lance des opération de lecture et d'écriture sur l'objet
 * Consultation. En lecture, elle permet de vérifier qu'on récupère bien les
 * objets associés En écriture, elle permet de voir ce qui se passe quand une
 * partie de l'association n'est pas surveillée par hibernate, et de mesurer
 * l'utilité de Cascade.
 */
public class ConsultationLanceur {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// lecture :
		Consultation consultation = (Consultation) session.get(
				Consultation.class, 1);
		System.out.println("recuperation de la consultation :" + consultation);
		System.out.println("medecin associé : " + consultation.getMedecin());
		System.out.println("patient associé : " + consultation.getPatient());

		// création
		Consultation nouvelleConsultation = new Consultation();
		// ce patient est déjà existant en BDD
		Patient patient = (Patient) session.get(Patient.class, 2);
		nouvelleConsultation.setPatient(patient);

		Medecin nouveauMedecin = new Medecin();
		nouveauMedecin.setNom("Boule");
		nouveauMedecin.setPrenom("Ma");
		nouvelleConsultation.setMedecin(nouveauMedecin);

		session.save(nouveauMedecin);

		// sans la suite, rien ne fonctionne!
		// option 1/ sauver aussi medecin!
		// session.save(nouvelleConsultation);
		// 2/ ou commenter la ligne précédente et jouer sur CASCADE
		// @*To*(cascade = CascadeType.PERSIST)

		transaction.commit();

	}
}
