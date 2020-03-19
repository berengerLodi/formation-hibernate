package fr.insee.tp.domaine;

import java.util.Date;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * Une consultation est la rencontre d'un {@link Medecin} et d'un
 * {@link Patient} à une date et un lieu donnés.
 * <p>
 * Une consultation est identifiée de manière unique par son identifiant.
 * <p>
 * Une consultation donne lieu à aucune, une ou plusieurs ordonnances (
 * {@link Ordonnance}) que le {@link Medecin} rédige.
 * <p>
 * Plusieurs consultations peuvent être triées entre elles en comparant la date
 * à laquelle elles ont eu lieu (ou l'identifiant en cas d'égalité).
 * */

public class Consultation implements Comparable<Consultation>, Identifiable {
	private Integer id;

	private Date date;

	private Adresse lieu;

	private Medecin medecin;

	private Patient patient;

	private Set<Ordonnance> ordonnances;

	public Consultation() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Adresse getLieu() {
		return lieu;
	}

	public void setLieu(Adresse lieu) {
		this.lieu = lieu;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<Ordonnance> getOrdonnances() {
		return ordonnances;
	}

	public void setOrdonnances(Set<Ordonnance> ordonnances) {
		this.ordonnances = ordonnances;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Consultation)) {
			return false;
		}
		final Consultation other = (Consultation) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Consultation.class)
				.add("date", this.date).add("medecin", this.medecin)
				.add("patient", this.patient).add("lieu", this.lieu)
				.add("ordonnances", this.ordonnances.size()).toString();
	}

	public int compareTo(Consultation other) {
		return ComparisonChain.start().compare(this.date, other.date)
				.compare(this.id, other.id).result();
	}
}
