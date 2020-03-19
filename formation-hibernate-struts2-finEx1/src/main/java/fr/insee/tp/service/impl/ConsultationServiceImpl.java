package fr.insee.tp.service.impl;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fr.insee.tp.dao.impl.ConsultationDAO;
import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Soin;
import fr.insee.tp.service.ConsultationService;

public class ConsultationServiceImpl implements ConsultationService {

	private ConsultationDAO consultationDAO;
	
	public ConsultationServiceImpl() {
		this.consultationDAO = new ConsultationDAO();
	}

	@Override
	public Consultation trouver(Integer id) {
		return consultationDAO.getById(id);
	}

	@Override
	public Consultation ajouterOuModifier(Consultation t) {
		return consultationDAO.insertOrUpdate(t);
	}

	@Override
	public SortedSet<Consultation> trouverTous() {
		return new TreeSet<Consultation>(consultationDAO.findAll());
	}

	@Override
	public List<? extends Soin> soins(Consultation consultation) {
		// TODO Auto-generated method stub
		return null;
	}
}
