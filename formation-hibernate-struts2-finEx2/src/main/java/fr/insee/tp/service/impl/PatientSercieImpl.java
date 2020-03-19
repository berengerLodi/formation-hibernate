package fr.insee.tp.service.impl;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Objects;

import fr.insee.tp.dao.impl.ConsultationDAO;
import fr.insee.tp.dao.impl.PatientDAO;
import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Maladie;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;
import fr.insee.tp.service.PatientService;

public class PatientSercieImpl implements PatientService {

	private PatientDAO patientDAO;
	private ConsultationDAO consultationDAO;

	public PatientSercieImpl() {
		this.patientDAO = new PatientDAO();
		this.consultationDAO = new ConsultationDAO();
	}

	@Override
	public Patient trouver(Integer id) {
		return patientDAO.getById(id);
	}

	@Override
	public Patient ajouterOuModifier(Patient t) {
		return patientDAO.insertOrUpdate(t);
	}

	@Override
	public SortedSet<Patient> trouverTous() {
		return new TreeSet<Patient>(patientDAO.findAll());
	}

	@Override
	public SortedSet<Medecin> medecins(Patient patient) {
		SortedSet<Medecin> medecins = new TreeSet<Medecin>();
		for (Consultation consultation : this.consultations(patient)) {
			medecins.add(consultation.getMedecin());
		}
		return medecins;
	}

	@Override
	public SortedSet<Consultation> consultations(Patient patient) {
		SortedSet<Consultation> consultations = new TreeSet<Consultation>();
		for (Consultation consultation : consultationDAO.findAll()) {
			if (Objects.equal(patient, consultation.getPatient())) {
				consultations.add(consultation);
			}
		}
		return consultations;
	}

	@Override
	public Maladie derniereMaladie(Patient patient) {
		if (CollectionUtils.isEmpty(patient.getDossierMedical().getMaladies())) {
			return null;
		}
		return Collections.max(patient.getDossierMedical().getMaladies());
	}
}
