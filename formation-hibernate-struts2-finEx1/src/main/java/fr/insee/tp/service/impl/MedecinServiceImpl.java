package fr.insee.tp.service.impl;

import java.util.Date;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import fr.insee.tp.dao.impl.ConsultationDAO;
import fr.insee.tp.dao.impl.MedecinDAO;
import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;
import fr.insee.tp.domaine.Soin;
import fr.insee.tp.service.MedecinService;

public class MedecinServiceImpl implements MedecinService {

	private MedecinDAO medecinDAO;
	private ConsultationDAO consultationDAO;

	public MedecinServiceImpl() {
		this.medecinDAO = new MedecinDAO();
		this.consultationDAO = new ConsultationDAO();
	}

	public void setMedecinDAO(MedecinDAO medecinDAO) {
		this.medecinDAO = medecinDAO;
	}

	@Override
	public Medecin trouver(Integer id) {
		return medecinDAO.getById(id);
	}

	@Override
	public Medecin ajouterOuModifier(Medecin medecin) {
		return medecinDAO.insertOrUpdate(medecin);
	}

	@Override
	public SortedSet<Medecin> trouverTous() {
		return new TreeSet<Medecin>(medecinDAO.findAll());
	}

	@Override
	public SortedSet<Patient> patients(Medecin medecin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Consultation> consultations(Medecin medecin) {
		SortedSet<Consultation> consultations = new TreeSet<Consultation>();
		for (Consultation consultation : consultationDAO.findAll()) {
			// if(Objects.equal(medecin, consultation.getMedecin())){
			// consultations.add(consultation);
			// }
		}
		return consultations;
	}

	@Override
	public SortedSet<Consultation> consultations(Medecin medecin, Date debut,
			Date fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<Soin, Integer> frequenceSoins(Medecin medecin) {
		// TODO Auto-generated method stub
		return null;
	}
}
