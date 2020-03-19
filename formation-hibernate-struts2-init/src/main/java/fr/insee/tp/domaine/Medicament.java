package fr.insee.tp.domaine;


/**
 * Un médicament est un {@link Soin} vendu par un pharmacien sur présentation
 * d'une {@link Ordonnance}.
 * */

public class Medicament extends Soin {

	private Boolean generique;
	private Integer quantite;

	public Boolean isGenerique() {
		return generique;
	}

	public void setGenerique(Boolean generique) {
		this.generique = generique;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

}
