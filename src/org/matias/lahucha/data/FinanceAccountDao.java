package org.matias.lahucha.data;

import java.util.List;

import org.matias.lahucha.model.FinanceAccount;

public interface FinanceAccountDao {

	FinanceAccount load(Long id);
	void save(FinanceAccount financeAccount);
	List<FinanceAccount> filter(String email);
}
