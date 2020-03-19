package fr.insee.tp.fonctionnementHibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.joda.time.DateTime;

import fr.insee.tp.dao.HibernateUtils;
import fr.insee.tp.domaine.DossierMedical;
import fr.insee.tp.domaine.Maladie;

public class DossierMedicalLanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DossierMedical dm = new DossierMedical();
		Maladie maladie = new Maladie();
		maladie.setDescription("gros rhume");
		DateTime d = new DateTime(2020, 01, 15, 0, 0);
		maladie.setDate(d.toDate());
		Set<Maladie> maladies = new HashSet<Maladie>();
		maladies.add(maladie);
		dm.setMaladies(maladies);

		Session session = HibernateUtils.open();

		// session.persist(maladie);
		session.persist(dm);
		// DossierMedical dossierMedical = (DossierMedical) session.get(DossierMedical.class, 13);
		// session.delete(dossierMedical);

		HibernateUtils.close();

	}

}
