package ie.gmit.rad.models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class College {
	private String colID;
	private String colName;
	private String colAddr;
	
	// Constructors
	public College() {}
	
	public College(String colID) {
		super();
		this.colID = colID;
	}
	
	public College(String colID, String colName, String colAddr) {
		this(colID);
		this.colName = colName;
		this.colAddr = colAddr;
	}
	
	// Getters and Setters
	public String getColId() {
		return colID;
	}

	public void setColId(String colID) {
		this.colID = colID;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColAddr() {
		return colAddr;
	}

	public void setColAddr(String colAddr) {
		this.colAddr = colAddr;
	}
}
