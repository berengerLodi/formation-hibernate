package fr.insee.tp.action;

import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import fr.insee.tp.domaine.Consultation;
import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.domaine.Patient;
import fr.insee.tp.service.ConsultationService;
import fr.insee.tp.service.PatientService;
import fr.insee.tp.service.impl.ConsultationServiceImpl;
import fr.insee.tp.service.impl.PatientSercieImpl;

/**
 * @author WEHDRC
 * 
 */
@SuppressWarnings("serial")
public class EnregistrerConsultationAction extends ActionSupport implements
		Preparable {

	private Consultation consultation;
	private Medecin medecin;
	private SortedSet<Patient> patients;

	private ConsultationService consultationService;
	private PatientService patientService;

	private static final Logger logger = Logger
			.getLogger(EnregistrerConsultationAction.class);

	public EnregistrerConsultationAction() {
		super();
		logger.info("new EnregistrerConsultationAction()");
	}

	@Override
	public void prepare() throws Exception {
		logger.info("prepare()");
		this.consultationService = new ConsultationServiceImpl();
		this.patientService = new PatientSercieImpl();
	}

	@Override
	public void validate() {
		logger.info("validate()");
		// this.medecin = consultation.getMedecin();
		this.patients = this.patientService.trouverTous();

		DateTime dateConsultation = new DateTime(this.consultation.getDate());
		if (dateConsultation != null && dateConsultation.isAfterNow()) {
			this.addActionError("La consultation ne peux avoir eu lieu après aujourd'hui");
		}
	}

	@Override
	public String execute() throws Exception {
		logger.info("execute()");
		this.consultation = consultationService.ajouterOuModifier(consultation);
		return SUCCESS;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		logger.info("setConsultation()");
		this.consultation = consultation;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public SortedSet<Patient> getPatients() {
		return patients;
	}
}
