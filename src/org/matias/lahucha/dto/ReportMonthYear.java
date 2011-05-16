package org.matias.lahucha.dto;

public class ReportMonthYear implements Comparable<ReportMonthYear>, Cloneable {

	private int month;
	private int year;
	
	public ReportMonthYear() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReportMonthYear(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

	public void nextMonth() {
		if (month < 12) {
			month += 1;
		} else {
			year += 1;
			month = 1;
		}
	}
	
	@Override
	public int compareTo(ReportMonthYear other) {
		if (this.year < other.year) {
			return -1;
		} else if (this.year > other.year) {
			return 1;
		} else {
			// equal year, required to compare month
			if (this.month < other.month) {
				return -1;
			} else if (this.month > other.month) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportMonthYear other = (ReportMonthYear) obj;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "ReportMonthYear [month=" + month + ", year=" + year + "]";
	}
}
