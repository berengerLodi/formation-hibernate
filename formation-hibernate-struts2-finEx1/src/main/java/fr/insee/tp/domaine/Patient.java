package fr.insee.tp.domaine;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import fr.insee.tp.service.Identifiable;

/**
 * <p>
 * Un patient est identifié de manière unique par sont identifiant. Il possède
 * un nom, un prénom et un numéro de sécurité sociale <code>nir</code>, une
 * adresse de type {@link Adresse} et un dossier médical de type
 * {@link DossierMedical}.
 * <p>
 * Le numéro de sécurité sociale doit vérifier l'expression régulière :
 * <code>^[1-3][0-9]{4}(2[AB]|[0-9]{2})[0-9]{6}$</code>.
 * <p>
 * On peut trier plusieurs patients entre eux en comparant d'abord le nom, puis
 * le prénom, puis le nir.
 */
@Entity
@Table(name = "PATIENT")
@SequenceGenerator(name = "SEQ_PATIENT", sequenceName = "SEQ_PATIENT")
public class Patient implements Comparable<Patient>, Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PATIENT")
	@javax.persistence.SequenceGenerator(name = "SEQ_PATIENT", sequenceName = "SEQ_PATIENT", allocationSize = 0)
	@Column(name = "ID")
	private Integer id;

	private Long nir;

	@OneToOne
	@JoinColumn(name = "iddossiermedical")
	private DossierMedical dossierMedical;
	private String nom;
	private String prenom;

	/**
	 * Regroupe les attributs l2Comp, l3Cadr, l4Voie, l5Disp, l6Post et l7Etrg
	 */
	@Embedded
	private Adresse adresse;

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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Patient)) {
			return false;
		}
		final Patient other = (Patient) object;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public String toString() {
		return Objects
			.toStringHelper(Patient.class)
			.add("id", this.id)
			.add("prenom", this.prenom)
			.add("nom", this.nom)
			.toString();
	}

	@Override
	public int compareTo(Patient other) {
		return ComparisonChain
			.start()
			.compare(this.nom, other.nom)
			.compare(this.prenom, other.prenom)
			.compare(this.nir, other.nir)
			.result();
	}

	// public DossierMedical getDossierMedical() {
	// return DossierMedical;
	// }
	//
	// public void setDossierMedical(DossierMedical dossierMedical) {
	// DossierMedical = dossierMedical;
	// }

	public Long getNir() {
		return nir;
	}

	public void setNir(Long nir) {
		this.nir = nir;
	}

}
