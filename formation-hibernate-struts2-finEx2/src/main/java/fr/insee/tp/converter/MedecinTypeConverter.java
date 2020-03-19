package fr.insee.tp.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import fr.insee.tp.domaine.Medecin;
import fr.insee.tp.service.MedecinService;
import fr.insee.tp.service.impl.MedecinServiceImpl;

public class MedecinTypeConverter extends StrutsTypeConverter {

	private MedecinService medecinService;
	
	public MedecinTypeConverter(){
		this.medecinService = new MedecinServiceImpl();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Integer id = Integer.valueOf(values[0]);
		return this.medecinService.trouver(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object object) {
		if(object == null || !(object instanceof Medecin)){
			return "";
		}
		return String.valueOf(((Medecin)object).getId());
	}
}
