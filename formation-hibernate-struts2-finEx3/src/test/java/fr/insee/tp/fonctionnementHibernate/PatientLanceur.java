package fr.insee.tp.fonctionnementHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.insee.tp.domaine.DossierMedical;
import fr.insee.tp.domaine.Patient;

public class PatientLanceur {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// lecture : on récupère l'objet et ses objets liés!
		Patient patient = (Patient) session.get(Patient.class, 1);
		System.out.println("recuperation du patient :" + patient);
		System.out.println("dossier médical du patient récupéré: "
				+ patient.getDossierMedical());

		// creation : on cree un patient... et son dossier médical : CASCADE?
		Patient nouveauPatient = new Patient();
		nouveauPatient.setNom("Zimir");
		nouveauPatient.setPrenom("Ka");
		nouveauPatient.setNir(3568211400305L);

		DossierMedical dm = new DossierMedical();

		// ajout bidirectionnel...
		nouveauPatient.setDossierMedical(dm);
		dm.setPatient(nouveauPatient);

		session.save(nouveauPatient);

		// Sans CASCADE ou sans les instructions suivantes, ca plante!
		// session.save(dm);
		// @OneToOne(cascade = CascadeType.PERSIST)

		transaction.commit();

	}
}
