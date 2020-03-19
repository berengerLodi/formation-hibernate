package fr.insee.tp.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import fr.insee.tp.domaine.Patient;
import fr.insee.tp.service.PatientService;
import fr.insee.tp.service.impl.PatientSercieImpl;

public class PatientTypeConverter extends StrutsTypeConverter {

	private PatientService patientService;
	
	public PatientTypeConverter(){
		this.patientService = new PatientSercieImpl();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Integer id = Integer.valueOf(values[0]);
		return this.patientService.trouver(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		if(object == null || !(object instanceof Patient)){
			return "";
		}
		return String.valueOf(((Patient)object).getId());
	}
}
