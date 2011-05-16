package org.matias.lahucha.form;

public class MovementForm {

	private Long finnanceAccountId;
	private Integer creationDate;
	private Integer creationMonth;
	private Integer creationYear;
	private Float amount;
	private String notes;
	
	public Long getFinnanceAccountId() {
		return finnanceAccountId;
	}
	public void setFinnanceAccountId(Long finnanceAccountId) {
		this.finnanceAccountId = finnanceAccountId;
	}
	public Integer getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Integer creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreationMonth() {
		return creationMonth;
	}
	public void setCreationMonth(Integer creationMonth) {
		this.creationMonth = creationMonth;
	}
	public Integer getCreationYear() {
		return creationYear;
	}
	public void setCreationYear(Integer creationYear) {
		this.creationYear = creationYear;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
