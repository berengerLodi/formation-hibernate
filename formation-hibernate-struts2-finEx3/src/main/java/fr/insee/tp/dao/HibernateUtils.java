package fr.insee.tp.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class HibernateUtils {

	private static SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * <p>Configuration d'Hibernate à partir de l'emplacement par défaut : <code>/src/main/resources/hibernate.cfg.xml</code>.
	 * <p>Création de la {@link SessionFactory} à partir du fichier de configuration.
	 * <em>
	 * <p>Note : Avec Hibernate 4+, la configuration ne se fait plus de cette manière.
	 * <p>Mais malheureusement, le composant de configuration de l'Insee n'est pas encore compatible avec les versions récentes d'Hibernate.
	 * </em>
	 * <code>
	 * <p>
	 * Configuration configuration = new Configuration();<br/>
	 * configuration.configure();<br/>
	 * serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();<br/>
	 * sessionFactory = configuration.buildSessionFactory(serviceRegistry);<br/>
	 * return sessionFactory;
	 * </p>
	 * </code>
	 * 
	 * */
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}
	
	/**
	 * <p>Ouvre une transaction Hibernate à partir de la session courante.
	 * <p>Il faut bien penser à la refermer pour que les éventuelles modifications soient bien prises en compte :
	 * {@link HibernateUtils#close()}.
	 * */
	public static Transaction open() {
		return sessionFactory.getCurrentSession().beginTransaction();
	}

	/**
	 * Ferme la transaction Hibernate courante et valide les changements éventuellements réalisés : phase de <em>commit</em>.
	 * */
	public static void close() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
}
