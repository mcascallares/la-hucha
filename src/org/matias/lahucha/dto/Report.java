package org.matias.lahucha.dto;

import java.text.DateFormatSymbols;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.matias.lahucha.model.FinanceAccount;

public class Report {

	private final ReportMonthYear from;
	private final ReportMonthYear until;
	private final String userEmail;
	private final String userName;
	private Map<FinanceAccount, Map<ReportMonthYear, ReportItem>> content;
	private Map<ReportMonthYear, ReportItem> totals;
	private String[] monthNames;
	
	public Report(ReportMonthYear from, ReportMonthYear until, String userEmail, String userName) {
		super();
		this.from = from;
		this.until = until;
		this.userEmail = userEmail;
		this.userName = userName;
		
		// <Account, <MonthYear, Cells>>
		content = new TreeMap<FinanceAccount, Map<ReportMonthYear, ReportItem>>(
				new Comparator<FinanceAccount>() {
					@Override
					public int compare(FinanceAccount o1, FinanceAccount o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
		totals = new HashMap<ReportMonthYear, ReportItem>();
		monthNames = new DateFormatSymbols().getMonths();
	}

	public void addMovement(FinanceAccount financeAccount, int month, int year, float value) {
		// report
		if (!content.containsKey(financeAccount)) {
			content.put(financeAccount, new TreeMap<ReportMonthYear, ReportItem>());
		}
		Map<ReportMonthYear, ReportItem> accountItems = content.get(financeAccount);
		ReportMonthYear monthYear = new ReportMonthYear(month, year);
		if (!accountItems.containsKey(monthYear)) {
			ReportItem reportItem = new ReportItem(value);
			accountItems.put(monthYear, reportItem);
		} else {
			accountItems.get(monthYear).update(value);
		}

		// totals
		if (!totals.containsKey(monthYear)) {
			ReportItem reportItem = new ReportItem(value);
			totals.put(monthYear, reportItem);
		} else {
			totals.get(monthYear).update(value);
		}
	}

	public int getMonthItemsCount() {
		if (from.equals(until)) {
			return 0;
		}
		int counter = 0;
		ReportMonthYear pointer = new ReportMonthYear(from.getMonth(), from.getYear());
		do {
			pointer.nextMonth();
			counter++;
		} while (!pointer.equals(until));

		return counter;
	}

	public Map<FinanceAccount, Map<ReportMonthYear, ReportItem>> getContent() {
		return content;
	}

	public Map<ReportMonthYear, ReportItem> getTotals() {
		return totals;
	}

	public ReportMonthYear getFrom() {
		return from;
	}

	public ReportMonthYear getUntil() {
		return until;
	}

	public String[] getMonthNames() {
		return monthNames;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserName() {
		return userName;
	}

}
