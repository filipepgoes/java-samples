package br.gov.previc.simple.batch.model;

public class PayrollInputRecord {

	private int id;
	private int baseSalary;

	public void setId(int id) {
		this.id=id;
		
	}

	public int getId() {
		return id;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary=baseSalary;
		
	}

}
