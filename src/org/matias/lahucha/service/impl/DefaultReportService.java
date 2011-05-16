package org.matias.lahucha.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.matias.lahucha.dto.Report;
import org.matias.lahucha.dto.ReportMonthYear;
import org.matias.lahucha.model.FinanceAccount;
import org.matias.lahucha.model.Movement;
import org.matias.lahucha.service.FinanceAccountService;
import org.matias.lahucha.service.MovementService;
import org.matias.lahucha.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultReportService implements ReportService {

	@Autowired
	private FinanceAccountService financeAccountService;

	@Autowired
	private MovementService movementService;

	@Override
	public Report generate(String userEmail, String userName, Date from, Date until) {
		Report ret = new Report(toReportMonthYear(from), toReportMonthYear(until), userEmail, userName);
		List<FinanceAccount> accounts = financeAccountService.loadByUserEmail(userEmail);
		for (FinanceAccount financeAccount : accounts) {
			List<Movement> movements = movementService.loadByAccountAndRange(financeAccount
					.getKey().getId(), new DateMidnight(from.getTime()).toDate(), new DateMidnight(
					until.getTime()).plusDays(1).toDate());
			for (Movement movement : movements) {
				DateTime dateTime = new DateTime(movement.getCreation().getTime());
				int month = dateTime.getMonthOfYear();
				int year = dateTime.getYear();
				ret.addMovement(financeAccount, month, year, movement.getAmount());
				// TODO add note?
			}
		}
		return ret;
	}

	private ReportMonthYear toReportMonthYear(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return new ReportMonthYear(dateTime.getMonthOfYear(), dateTime.getYear());
	}

}
