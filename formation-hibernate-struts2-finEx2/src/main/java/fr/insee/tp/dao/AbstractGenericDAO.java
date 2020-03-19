package fr.insee.tp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * <p>Cette classe abstraite fournie une implémentation de base des fonction CRUD pour les objets métiers.
 * <p><strong>Attention :</strong> faut forcément hériter de cette classe pour pouvoir l'utiliser.
 * Plus précisemment, il est nécessaire de préciser un vrai type pour <code>T</code> et <code>ID</code>.
 */
public abstract class AbstractGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;
	private static final Logger logger = Logger.getLogger(AbstractGenericDAO.class);

	@SuppressWarnings("unchecked")
	public AbstractGenericDAO() {
		ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
		Type[] actualTypes = parameterizedType.getActualTypeArguments();
		this.persistentClass = (Class<T>)actualTypes[0];
		this.sessionFactory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		logger.debug("getById(" + this.persistentClass.getCanonicalName() + ")");
		try {
			T entity = (T)this.getSession().get(this.persistentClass, id);
			return entity;
		}
		catch (HibernateException exception) {
			logger.error(exception);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T loadById(ID id) {
		logger.debug("findById(" + this.persistentClass.getCanonicalName() + ")");
		try {
			T entity = (T)this.getSession().load(this.persistentClass, id);
			return entity;
		}
		catch (HibernateException exception) {
			logger.error(exception);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		logger.debug("findAll(" + this.persistentClass.getCanonicalName() + ")");
		Criteria criteria = this.getSession().createCriteria(this.persistentClass);
		try {
			return criteria.list();
		}
		catch (HibernateException exception) {
			exception.printStackTrace();
			logger.error(exception);
		}
		return Collections.emptyList();
	}

	@Override
	public T insertOrUpdate(T entity) {
		logger.debug("insertOrUpdate(" + this.persistentClass.getCanonicalName() + ")");
		try {
			this.getSession().saveOrUpdate(entity);
		}
		catch (HibernateException exception) {
			logger.error(exception);
		}
		return entity;
	}

	@Override
	public T insert(T entity) {
		logger.debug("insert(" + this.persistentClass.getCanonicalName() + ")");
		try {
			this.getSession().save(entity);
		}
		catch (HibernateException exception) {
			logger.error(exception);
		}
		return entity;
	}

	@Override
	public void delete(T entity) {
		logger.debug("delete(" + this.persistentClass.getCanonicalName() + ")");
		try {
			this.getSession().delete(entity);
		}
		catch (HibernateException exception) {
			logger.error(exception);
		}
	}
}
