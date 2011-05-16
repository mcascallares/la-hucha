package org.matias.lahucha.service;

import java.util.Date;
import java.util.List;

import org.matias.lahucha.model.Movement;

public interface MovementService {

	void save(Movement movement);
	List<Movement> loadByAccountAndRange(Long financeAccountId, Date from, Date until);

}
