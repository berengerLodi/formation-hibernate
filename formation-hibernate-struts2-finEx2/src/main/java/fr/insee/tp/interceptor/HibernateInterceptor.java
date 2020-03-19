package fr.insee.tp.interceptor;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import fr.insee.tp.dao.HibernateUtils;

/**
 * Intercepteur qui va gérer les accès à la base de données. Pour chaque action,
 * on ouvre une transaction, on exécute l'action, puis on essaie de commiter la
 * transaction
 */
@SuppressWarnings("serial")
public class HibernateInterceptor implements Interceptor {

	private static Logger log = Logger.getLogger(HibernateInterceptor.class);

	@Override
	public void init() {}
	
	@Override
	public void destroy() {
		HibernateUtils.getSessionFactory().close();
	}

	/**
	 * Mise en place du filtre : on démarre une transaction, on passe la main,
	 * puis on commit (si possible) la transaction
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		Transaction transaction = HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
		
		String result = invocation.invoke();
		
		try {
			transaction.commit();
		}
		catch (HibernateException commitException) {
			log.error("Erreur lors du commit : " + commitException.getMessage());
			try {
				transaction.rollback();
			}
			catch (HibernateException rollbackException) {
				log.error("Erreur lors du rollback : " + rollbackException.getMessage());
			} 
		}
		return result;
	}
}
