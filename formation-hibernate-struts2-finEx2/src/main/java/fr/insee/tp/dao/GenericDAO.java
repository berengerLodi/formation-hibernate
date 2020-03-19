package fr.insee.tp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * <p>Cette interface définie les fonctions de bases (CRUD) pour les objets persistants de l'application.
 * <dl>
 * <dt>Create</dt>
 * 	<dd>{@link GenericDAO#insert(Object)}</dd>
 * 	<dd>{@link GenericDAO#insertOrUpdate(Object)}</dd>
 * <dt>Read</dt>
 * 	<dd>{@link GenericDAO#getById(Serializable)}</dd>
 * 	<dd>{@link GenericDAO#loadById(Serializable)}</dd>
 * 	<dd>{@link GenericDAO#findAll()}</dd>
 * <dt>Update</dt>
 * 	<dd>{@link GenericDAO#insertOrUpdate(Object)}</dd>
 * <dt>Delete</dt>
 * 	<dd>{@link GenericDAO#delete(Object)}</dd>
 * </dl>
 * <p>Elle permet aussi de récupérer la session courante : {@link GenericDAO#getSession()}.
 * */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * <p>Renvoie la session Hibernate courante.
	 * */
	Session getSession();
	
	/**
	 * <p>Renvoie l'instance de la classe <code>T</code> associée à l'identifiant <code>id</code>, ou <code>null</code> si aucune instance n'est trouvée pour l'identifiant <code>id</code>.
	 * */
	T getById(ID id);
	
	/**
	 * Renvoie l'instance de la classe <code>T</code> associée à l'identifiant <code>id</code>, ou un proxy si aucune instance n'est trouvée pour l'identifiant <code>id</code>.
	 * */
	T loadById(ID id);
	
	/**
	 * <p>Renvoie tous les instances de la classe <code>T</code>.
	 * */
	List<T> findAll();
	
	/**
	 * <p>Insère l'objet <code>entity</code> dans la base
	 * <ul>
	 * <li>si son identifiant est nul ou vide,
	 * <li>ou si son identifiant n'existe pas déjà dans la base.
	 * </ul>
	 * <p>Met à jour l'objet sinon.
	 * @return L'objet mis à jour ou inséré --- qui comporte donc <strong>toujours</strong> un identifiant non nul et non vide.
	 * */
	T insertOrUpdate(T entity);
	
	/**
	 * <p>Insère l'objet <code>entity</code> dans la base.
	 * */
	T insert(T entity);
	
	/**
	 * <p>Supprime l'objet <code>entity</code> de la base.
	 * */
	void delete(T entity);
}