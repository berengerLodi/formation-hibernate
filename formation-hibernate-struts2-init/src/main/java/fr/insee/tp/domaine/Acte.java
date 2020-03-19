package fr.insee.tp.domaine;

import com.google.common.base.Objects;

public class Acte extends Soin {

	private Boolean remboursable;

	public Boolean getRemboursable() {
		return remboursable;
	}

	public void setRemboursable(Boolean remboursable) {
		this.remboursable = remboursable;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Acte.class).add("id", super.getId())
				.add("nom", this.getNom()).add("prix", this.getPrix())
				.toString();
	}

}
