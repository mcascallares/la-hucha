package org.matias.lahucha.service.impl;

import java.util.List;

import org.matias.lahucha.data.FinanceAccountDao;
import org.matias.lahucha.model.FinanceAccount;
import org.matias.lahucha.service.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFinanceAccountService implements FinanceAccountService {

	@Autowired
	private FinanceAccountDao financeAccountDao;

	@Override
	public FinanceAccount load(Long id) {
		return financeAccountDao.load(id);
	}

	@Override
	public void save(FinanceAccount financeAccount) {
		financeAccountDao.save(financeAccount);
	}

	@Override
	public List<FinanceAccount> loadByUserEmail(String email) {
		return financeAccountDao.filter(email);
	}

}
