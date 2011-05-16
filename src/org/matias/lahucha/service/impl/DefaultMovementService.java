package org.matias.lahucha.service.impl;

import java.util.Date;
import java.util.List;

import org.matias.lahucha.data.MovementDao;
import org.matias.lahucha.model.Movement;
import org.matias.lahucha.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMovementService implements MovementService {

	@Autowired
	private MovementDao movementDao;
	
	@Override
	public void save(Movement movement) {
		movementDao.save(movement);
	}

	@Override
	public List<Movement> loadByAccountAndRange(Long financeAccountId, Date from, Date until) {
		return movementDao.filter(financeAccountId, from, until);
	}

}
