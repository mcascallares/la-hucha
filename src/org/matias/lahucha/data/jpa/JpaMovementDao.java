package org.matias.lahucha.data.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.matias.lahucha.data.MovementDao;
import org.matias.lahucha.jpa.support.EntityManagerFactoryFacade;
import org.matias.lahucha.model.Movement;
import org.springframework.stereotype.Repository;

@Repository
public class JpaMovementDao implements MovementDao {

	public void save(Movement movement) {
		EntityManager entityManager = EntityManagerFactoryFacade.get().createEntityManager();
		entityManager.persist(movement);
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movement> filter(Long financeAccountId, Date from, Date until) {
		StringBuilder query = new StringBuilder("select m from Movement m where m.financeAccountId = :accountId");
		if (from != null) {
			query.append(" and m.creation >= :from");
		}
		if (until != null) {
			query.append(" and m.creation < :until");
		}
		EntityManager entityManager = EntityManagerFactoryFacade.get().createEntityManager();
		Query q = entityManager.createQuery(query.toString());
		q.setParameter("accountId", financeAccountId).setParameter("from", from).setParameter("until", until);
		return q.getResultList();
	}

}
