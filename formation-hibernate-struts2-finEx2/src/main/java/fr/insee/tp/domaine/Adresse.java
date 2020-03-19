package fr.insee.tp.domaine;

import javax.persistence.Embeddable;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * <p>
 * Une adresse est constituée d'une voie, ex :
 * <code>"18 Boulevard Adolphe Pinard"</code>, et d'une ville ex. :
 * <code>"75014 Paris"</code>.
 * <p>
 * Une adresse est égale à une autre si et seulement si sa voie et sa ville sont
 * identiques.
 * <p>
 * On peut trier plusieurs adresses entre elles en comparant d'abord la ville,
 * puis la voie.
 * */
@Embeddable
public class Adresse implements Comparable<Adresse> {

	private String voie;
	private String ville;

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.voie, this.ville);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Adresse)) {
			return false;
		}
		final Adresse other = (Adresse) object;
		return Objects.equal(this.voie, other.voie)
				&& Objects.equal(this.ville, other.ville);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Adresse.class).add("voie", this.voie)
				.add("ville", this.ville).toString();
	}

	public int compareTo(Adresse other) {
		return ComparisonChain.start().compare(this.ville, other.ville)
				.compare(this.voie, other.voie).result();
	}
}
