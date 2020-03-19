package fr.insee.tp.domaine;

import java.util.Date;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * Une maladie correspond à une pathologie contractée par un patient à une date
 * donnée.
 * <p>
 * Chaque maladie est identifiée de manière unique par son identifiant.
 * <p>
 * Une maladie comporte une description rédigée par un médecin.
 * <p>
 * Plusieurs maladie peuvent être triées les unes entre les autres suivant leur
 * date.
 * */

public class Maladie implements Comparable<Maladie>, Identifiable {
	private Integer id;

	private String description;

	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Maladie)) {
			return false;
		}
		final Maladie other = (Maladie) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Maladie.class).add("id", this.id)
				.add("description", this.description).toString();
	}

	@Override
	public int compareTo(Maladie other) {
		return ComparisonChain.start().compare(this.date, other.date).result();
	}
}
