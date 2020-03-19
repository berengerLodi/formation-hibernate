package fr.insee.tp.domaine;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PbId {
   
	@EmbeddedId
	private PbIdId id;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PbId [id=" + id.toString() + ", description=" + description
				+ "]";
	}

	public PbIdId getId() {
		return id;
	}

	public void setId(PbIdId id) {
		this.id = id;
	}

}
