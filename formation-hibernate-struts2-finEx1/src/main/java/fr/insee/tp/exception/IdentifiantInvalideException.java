package fr.insee.tp.exception;

@SuppressWarnings("serial")
public class IdentifiantInvalideException extends FormationException {

	public IdentifiantInvalideException(Integer id) {
		super("L'identifiant [" + id + "] est invalide.");
	}
}
