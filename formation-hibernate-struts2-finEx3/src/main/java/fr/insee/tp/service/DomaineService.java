package fr.insee.tp.service;

import java.util.SortedSet;

/**
 * <p>Service générique pour les classes métier.
 * <p>Pour bénéficier de ce service générique, les classes doivent :
 * <ol>
 * <li>être naturellement {@link Comparable} par rapport à elle même ;
 * <li>et être {@link Identifiable}, c'est-à-dire posséder un identifiant <code>id</code> qui est un {@link Integer}.
 * </ol>
 * */
public interface DomaineService<T extends Comparable<T> & Identifiable> {

	/**
	 * Recherche un objet par son identifiant.
	 * Si l'objet n'est pas trouvé, <code>null</code> est renvoyé.
	 * @param id - L'identifiant de l'objet qu'on recherche.
	 * @return L'objet identifié par le paramètre <code>id</code> ou <code>null</code> s'il n'existe pas.
	 * */
	T trouver(Integer id);
	
	/**
	 * Insère l'objet <code>t</code> dans la base.
	 * Si <code>t</code> possède un identifiant non nul, l'objet correspondant à cet identifiant est modifié.
	 * @param t - L'objet à insérer ou à modifier en base.
	 * @return - L'objet <code>t</code> auquel a été ajouté un identifiant dans le cas d'une insertion.
	 * */
	T ajouterOuModifier(T t);
	
	/**
	 * <p>Retourne tous les objets de la classe <code>T</code> enregistrés en base.
	 * <p>La collection retournée :
	 * <ol>
	 * <li>ne comporte pas de doublons ;
	 * <li>est triée selon l'ordre naturel de la classe <code>T</code>.
	 * </ol>
	 * @return L'ensemble trié sans doublon des objets de la classe <code>T</code>.
	 * */
	SortedSet<T> trouverTous();
}
