package fr.insee.tp.exception;

/**
 * Exception mère de toutes les exceptions métier du TP pour la formation.
 * */
@SuppressWarnings("serial")
public abstract class FormationException extends Exception {

	public FormationException(String message) {
		super(message);
	}
}
