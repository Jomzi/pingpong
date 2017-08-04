package ie.gmit.rad.models;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {
	private String sId;
	private Course course;
	private String firstName;
	private String lastName;
	private String address;
	
	// Constructors
	public Student() {
		course = new Course();
	}
	
	public Student(String SId, Course course, String firstName, String lastName, String address) {
		super();
		this.sId = sId;
		this.course = course;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	// Getters and Setters
	public String getSId() {
		return sId;
	}

	public void setSId(String sId) {
		this.sId = sId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCId(Course course) {
		this.course =course;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
