package fr.insee.tp.dao.impl;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import fr.insee.tp.dao.GenericDAO;
import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Ordonnance;
import fr.insee.tp.domaine.Patient;

public class ConsultationDAOTest {

	@Test
	public void testInsertionSansDAO() throws Exception {

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

	@Test
	public void testInsertAvecDAO() throws Exception {

		HibernateUtils.open();
		GenericDAO<Consultation, Integer> consultationDAO = new ConsultationDAO();
		GenericDAO<Patient, Integer> patientDAO = new PatientDAO();

		// lecture :
		Consultation consultation = consultationDAO.getById(2);
		System.out.println("recuperation de la consultation :" + consultation);
		System.out.println("medecin associé : " + consultation.getMedecin());
		System.out.println("patient associé : " + consultation.getPatient());

		// création
		Consultation nouvelleConsultation = new Consultation();
		// ce patient est déjà existant en BDD
		Patient patient = patientDAO.getById(2);
		nouvelleConsultation.setPatient(patient);

		Medecin nouveauMedecin = new Medecin();
		nouveauMedecin.setNom("Hill");
		nouveauMedecin.setPrenom("Jeck");
		nouvelleConsultation.setMedecin(nouveauMedecin);

		// sans la suite, rien ne fonctionne!
		// option 1/ sauver aussi medecin!
		// GenericDAO<Medecin, Integer> medecinDAO = new MedecinDAO();
		// medecinDAO.insertOrUpdate(nouveauMedecin);
		// 2/ ou commenter la ligne précédente et jouer sur CASCADE
		// @Cascade(value = { org.hibernate.annotations.CascadeType.PERSIST,
		// org.hibernate.annotations.CascadeType.SAVE_UPDATE })

		consultationDAO.insertOrUpdate(nouvelleConsultation);
		HibernateUtils.close();
	}

	@Test
	public void testBidirectionnelAvecOrdonnance() throws Exception {
		HibernateUtils.open();
		GenericDAO<Consultation, Integer> consultationDAO = new ConsultationDAO();
		GenericDAO<Patient, Integer> patientDAO = new PatientDAO();

		// création
		Consultation nouvelleConsultation = new Consultation();
		// ce patient est déjà existant en BDD
		Patient patient = patientDAO.getById(2);
		nouvelleConsultation.setPatient(patient);

		Medecin nouveauMedecin = new Medecin();
		nouveauMedecin.setNom("Hill");
		nouveauMedecin.setPrenom("Jeck");
		nouvelleConsultation.setMedecin(nouveauMedecin);

		Ordonnance o1 = new Ordonnance();
		Ordonnance o2 = new Ordonnance();
		if (nouvelleConsultation.getOrdonnances() == null) {
			nouvelleConsultation.setOrdonnances(new HashSet<Ordonnance>());
		}
		nouvelleConsultation.getOrdonnances().add(o1);
		nouvelleConsultation.getOrdonnances().add(o2);
		o1.setConsultation(nouvelleConsultation);
		o2.setConsultation(nouvelleConsultation);

		consultationDAO.insertOrUpdate(nouvelleConsultation);
		HibernateUtils.close();
	}
}
