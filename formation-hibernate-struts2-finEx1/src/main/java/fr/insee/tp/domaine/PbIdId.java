package fr.insee.tp.domaine;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PbIdId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer matricule;

	private Integer code;

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "PbId [id=" + matricule + " -- " + code + "]";
	}

}
