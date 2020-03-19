package fr.insee.tp.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.DossierMedical;
import fr.insee.tp.domaine.Patient;

public class PatientDAOTest {
	@Test
	public void testSansDAO() throws Exception {

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

	@Test
	public void testAvecDAO() throws Exception {

		HibernateUtils.open();

		GenericDAO<Patient, Integer> patientDAO = new PatientDAO();

		// lecture : on récupère l'objet et ses objets liés!
		Patient patient = patientDAO.getById(1);
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

		patientDAO.insertOrUpdate(nouveauPatient);

		// Sans CASCADE ca plante

		HibernateUtils.close();

	}
}
