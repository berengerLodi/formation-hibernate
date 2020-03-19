package fr.insee.tp.action;

import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.service.MedecinService;
import fr.insee.tp.service.impl.MedecinServiceImpl;

@SuppressWarnings("serial")
public class ListeConsultationsAction extends ActionSupport {

	private Medecin medecin;
	private SortedSet<Consultation> consultations;
	
	private MedecinService medecinService = new MedecinServiceImpl();
	
	@Override
	public String execute() throws Exception {
		this.consultations = medecinService.consultations(medecin);
		return SUCCESS;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public SortedSet<Consultation> getConsultations() {
		return consultations;
	}
}
