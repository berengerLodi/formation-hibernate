package fr.insee.tp.domaine;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * Chaque patient dispose d'un et d'un seul dossier médical qui recense la liste
 * des {@link Maladie} qu'il a déjà contracté.
 */
@Entity
@Table(name = "DOSSIERMEDICAL")
@SequenceGenerator(name = "SEQ_DOSSIERMEDICAL", sequenceName = "SEQ_DOSSIERMEDICAL")
public class DossierMedical implements Comparable<DossierMedical>, Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOSSIERMEDICAL")
	@javax.persistence.SequenceGenerator(name = "SEQ_DOSSIERMEDICAL", sequenceName = "SEQ_DOSSIERMEDICAL", allocationSize = 0)
	@Column(name = "ID")
	private Integer id;

	@OneToOne(mappedBy = "dossierMedical")
	private Patient patient;

	// @OneToMany(fetch = FetchType.EAGER)
	// @Fetch(FetchMode.JOIN)
	@OneToMany
	@JoinColumn(name = "IDDOSSIERMEDICAL")
	private Set<Maladie> maladies;

	public DossierMedical() {
		this.maladies = new HashSet<Maladie>();
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<Maladie> getMaladies() {
		return maladies;
	}

	public void setMaladies(Set<Maladie> maladies) {
		this.maladies = maladies;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof DossierMedical)) {
			return false;
		}
		final DossierMedical other = (DossierMedical) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public int compareTo(DossierMedical other) {
		return ComparisonChain.start().compare(this.id, other.id).result();
	}

	@Override
	public String toString() {
		return "DossierMedical [id=" + id + ", patient=" + patient
			+ ", maladies=" + maladies + "]";
	}

}
