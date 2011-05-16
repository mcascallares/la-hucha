package org.matias.lahucha.service;

import java.util.List;

import org.matias.lahucha.model.FinanceAccount;

public interface FinanceAccountService {

	FinanceAccount load(Long id);
	void save(FinanceAccount financeAccount);
	List<FinanceAccount> loadByUserEmail(String email);
}
