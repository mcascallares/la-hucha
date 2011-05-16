package org.matias.lahucha.dto;

public class ReportItem {

	private float positive;
	private float negative;

	public ReportItem() {
		super();
	}

	public ReportItem(float value) {
		super();
		update(value);
	}

	public void update(float value) {
		if (value > 0) {
			positive += value;
		} else if (value < 0) {
			negative += Math.abs(value);
		}
	}

	public float getPositive() {
		return positive;
	}

	public float getNegative() {
		return negative;
	}

	@Override
	public String toString() {
		return "ReportItem [positive=" + positive + ", negative=" + negative + "]";
	}

}
