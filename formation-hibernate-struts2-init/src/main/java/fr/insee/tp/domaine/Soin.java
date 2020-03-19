package fr.insee.tp.domaine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>Un soin correspond à un élément d'une certaine nomenclature de soins médicaux.
 * <p>Chaque soin est identifié de manière unique par son identifiant.
 * <p>Un soin comporte un nom et un prix fixés par la nomenclature.
 * <p>Plusieurs soins peuvent être triés les unes entre les autres suivant leur nom.
 * */

public abstract class Soin implements Comparable<Soin>, Identifiable {
	
	private Integer id;
	private String nom;
	private Float prix;
	
	public Soin(){}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
	    if (object == null || !(object instanceof Soin)) {
	        return false;
	    }
	    final Soin other = (Soin) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public String toString() {
		return
			Objects.toStringHelper(Soin.class)
			.add("id", this.id)
			.add("nom", this.nom)
			.add("prix", this.prix)
			.toString()
		;
	}

	public int compareTo(Soin other) {
		return ComparisonChain.start().compare(this.id, other.id).result();
	}
}
