package br.gov.previc.simple.batch.model;

public class PayrollRecord {
	private String monthYear;
	private int empID;
	private float base;
	private float tax;
	private float net;
	private float bonus = 0;
	
	
	public void setBase(int base) {
		this.base = base;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getNet() {
		return net;
	}
	public void setNet(float net) {
		this.net = net;
	}
	public float getBase() {
		return base;
	}
	public void setBase(float base) {
		this.base = base;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	
	

}
