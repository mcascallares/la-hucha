package org.matias.lahucha.data.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.matias.lahucha.data.FinanceAccountDao;
import org.matias.lahucha.jpa.support.EntityManagerFactoryFacade;
import org.matias.lahucha.model.FinanceAccount;
import org.springframework.stereotype.Repository;

@Repository
public class JpaFinanceAccountDao implements FinanceAccountDao {

	public FinanceAccount load(Long id) {
		return EntityManagerFactoryFacade.get().createEntityManager().find(FinanceAccount.class, id);
	}
	
	public void save(FinanceAccount financeAccount) {
		EntityManager entityManager = EntityManagerFactoryFacade.get().createEntityManager();
		entityManager.persist(financeAccount);
		entityManager.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<FinanceAccount> filter(String email) {
		EntityManager entityManager = EntityManagerFactoryFacade.get().createEntityManager();
		Query query = entityManager
				.createQuery("select fa from FinanceAccount fa where fa.userEmail = :email order by name");
		query.setParameter("email", email);
		List<FinanceAccount> ret = (List<FinanceAccount>) query.getResultList();
		return ret;
	}
}
