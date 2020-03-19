package fr.insee.tp.domaine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PbIdMauvaiseGestionDesId {

   @Id
   private Integer matricule;

   private Integer code;
   
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PbId [matricule=" + matricule + ", code=" + code + "] + , description=" + description
				+ "]";
	}

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

}
