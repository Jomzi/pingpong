package ie.gmit.rad.models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Course {
	private College college;
	private String cID;
	private String cName;

	
	// Constructors
	public Course() {
		college = new College();
	}

	public Course(College college, String cID, String cName) {
		super();
		this.college = college;
		this.cID = cID;
		this.cName = cName;
	}
	
	// Getters and Setters
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public String getCId() {
		return cID;
	}

	public void setCId(String cID) {
		this.cID = cID;
	}

	public String getCName() {
		return cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	
}
