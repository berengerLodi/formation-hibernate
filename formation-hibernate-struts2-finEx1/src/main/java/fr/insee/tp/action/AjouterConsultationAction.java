package fr.insee.tp.action;

import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;
import fr.insee.tp.service.PatientService;
import fr.insee.tp.service.impl.PatientSercieImpl;

@SuppressWarnings("serial")
public class AjouterConsultationAction extends ActionSupport {

	private Medecin medecin;
	private SortedSet<Patient> patients;
	
	private PatientService patientService = new PatientSercieImpl();
	
	@Override
	public String execute() throws Exception {
		this.patients = patientService.trouverTous();
		return SUCCESS;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public SortedSet<Patient> getPatients() {
		return patients;
	}
}
