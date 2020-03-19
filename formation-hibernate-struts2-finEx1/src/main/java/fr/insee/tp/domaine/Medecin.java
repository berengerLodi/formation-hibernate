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
 * Un medecin est identifié de manière unique par sont identifiant. Il possède
 * un nom, un prénom et un numéro de téléphone. Un entier (1 ou 2) représente le
 * secteur dans lequel le médecin est conventionné.
 * <p>
 * On peut trier plusieurs médecins entre eux en comparant d'abord le nom, puis
 * le prénom, puis l'identifiant.
 */
@Entity
public class Medecin implements Comparable<Medecin>, Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEDECIN")
	@SequenceGenerator(name = "SEQ_MEDECIN", sequenceName = "SEQ_MEDECIN", allocationSize = 1)
	private Integer id;

	private String nom;

	private String prenom;

	private String telephone;

	private Integer secteur;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSecteur() {
		return secteur;
	}

	public void setSecteur(Integer secteur) {
		this.secteur = secteur;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Medecin)) {
			return false;
		}
		final Medecin other = (Medecin) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public String toString() {
		return Objects
			.toStringHelper(Medecin.class)
			.add("id", this.id)
			.add("prenom", this.prenom)
			.add("nom", this.nom)
			.toString();
	}

	@Override
	public int compareTo(Medecin other) {
		return ComparisonChain
			.start()
			.compare(this.nom, other.nom)
			.compare(this.prenom, other.prenom)
			.compare(this.id, other.id)
			.result();
	}
}
