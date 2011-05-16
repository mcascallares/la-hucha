package org.matias.lahucha.data;

import java.util.Date;
import java.util.List;

import org.matias.lahucha.model.Movement;

public interface MovementDao {

	void save(Movement movement);
	List<Movement> filter(Long financeAccountId, Date from, Date until);

}
