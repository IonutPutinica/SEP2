package domain.model;

import java.io.Serializable;

public class CPR implements ICPR, Serializable {
	
	private String cpr;
	private static final long serialVersionUID = 1L;
	
	public CPR(String cpr) {
		this.cpr = cpr;
	}
	
	public void setCPR(String cpr) {
		this.cpr = cpr;
	}
	
	public String getCPR() {
		return cpr;
	}

}
