package fr.insee.tp.action;

import java.util.Date;
import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.service.MedecinService;
import fr.insee.tp.service.impl.MedecinServiceImpl;

@SuppressWarnings("serial")
public class ListeMedecinsAction extends ActionSupport {

	private Date date;
	private SortedSet<Medecin> medecins;
	
	private MedecinService medecinService = new MedecinServiceImpl();
	
	@Override
	public String execute() throws Exception {
		// Date du jour
		this.date = new Date();
		
		// Liste des médecins
		this.medecins = this.medecinService.trouverTous();
		
		return SUCCESS;
	}

	public Date getDate() {
		return date;
	}

	public SortedSet<Medecin> getMedecins() {
		return medecins;
	}
}
