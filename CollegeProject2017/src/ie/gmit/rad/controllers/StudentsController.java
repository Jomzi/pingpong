package ie.gmit.rad.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ie.gmit.rad.dao.DAO;
import ie.gmit.rad.models.Student;

@ManagedBean
@SessionScoped
public class StudentsController {
	private ArrayList<Student> students;
	private DAO dao;
	
    // Constructors
	public StudentsController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the students from the database and store them in an instance variable
	public void loadStudents() {
		try {
			students = dao.getStudents();
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to add the new student to the database
	public String addStudent(Student student) {
		try {
			dao.addStudent(student);
			return "list_students";
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	// Add the selected student to ExternalContext 
	public String allDetails(Student student) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("student", student);
		
		return "student_details";
	}
	
	// Get the student from the ExternalContext
	public Student getStudent() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Student student = (Student)sessionMap.get("student");
		
		return student;
	}
	
	// Getters and Setters
	public ArrayList<Student> getStudents() {
		return students;
	}
}