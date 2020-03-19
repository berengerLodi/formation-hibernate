package fr.insee.tp.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * À l'issu d'une {@link Consultation}, le médecin dresse aucune, une ou
 * plusieurs ordonnance à l'intention de différents professionnels de la santé.
 * <p>
 * Une ordonnance est identifiée de manière unique par son identifiant.
 * <p>
 * Une ordonnance doit normalement être tamponnée par le médecin.
 * <p>
 * Elle contient une liste de soins ({@link Soin}). Ces soins sont :
 * <ol>
 * <li>soit des médicaments
 * <li>soit des soins de soins prodigués par des professions intermédiaires de
 * la santé ou d'autres médecins spécialistes.
 * </ol>
 * <p>
 * Plusieurs ordonnances peuvent être triées entre elles en comparant leurs
 * identifiants.
 * 
 * */
@Entity
@SequenceGenerator(name = "SEQ_ORDONNANCE", sequenceName = "SEQ_ORDONNANCE")
public class Ordonnance implements Comparable<Ordonnance>, Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDONNANCE")
	@javax.persistence.SequenceGenerator(name = "SEQ_ORDONNANCE", sequenceName = "SEQ_ORDONNANCE", allocationSize = 0)
	private Integer id;
	private Boolean tampon;

	// private Consultation consultation;

	// private Set<Soin> soins;

	public Ordonnance() {
		// this.soins = new HashSet<Soin>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getTampon() {
		return tampon;
	}

	public void setTampon(Boolean tampon) {
		this.tampon = tampon;
	}

	// public Consultation getConsultation() {
	// return consultation;
	// }
	//
	// public void setConsultation(Consultation consultation) {
	// this.consultation = consultation;
	// }
	//
	// public Set<Soin> getSoins() {
	// return soins;
	// }
	//
	// public void setSoins(Set<Soin> soins) {
	// this.soins = soins;
	// }

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Ordonnance)) {
			return false;
		}
		final Ordonnance other = (Ordonnance) object;
		return Objects.equal(this.id, other.id);
	}

	// @Override
	// public String toString() {
	// return Objects.toStringHelper(Ordonnance.class).add("id", this.id)
	// .add("tampon", this.tampon)
	// .add("consulation", this.consultation.getId())
	// .add("soins", this.soins.size()).toString();
	// }

	public int compareTo(Ordonnance other) {
		return ComparisonChain.start().compare(this.id, other.id).result();
	}
}
