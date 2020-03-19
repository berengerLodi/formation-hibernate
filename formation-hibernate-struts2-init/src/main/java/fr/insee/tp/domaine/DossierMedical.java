package fr.insee.tp.domaine;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * Chaque patient dispose d'un et d'un seul dossier médical qui recense la liste
 * des {@link Maladie} qu'il a déjà contracté.
 * */
public class DossierMedical implements Comparable<DossierMedical>, Identifiable {

	private Integer id;

	private Patient patient;

	private Set<Maladie> maladies;

	public DossierMedical() {
		this.maladies = new HashSet<Maladie>();
	}

	public Integer getId() {
		return id;
	}

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
	public String toString() {
		return Objects.toStringHelper(DossierMedical.class).add("id", this.id)
				.add("patient", this.patient)
				.add("maladies", this.maladies.size()).toString();
	}

	public int compareTo(DossierMedical other) {
		return ComparisonChain.start().compare(this.id, other.id).result();
	}

}
