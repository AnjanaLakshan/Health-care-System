package com.InsuranceProvider.Model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Provider {
	
	private int id;
	private String name;
	private String nic;
	private String income;
	private String level;
	private String claim;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", nic=" + nic + ", income=" + income + ", level=" + level + ", claim=" + claim
				+ "]";
	}
	
}
