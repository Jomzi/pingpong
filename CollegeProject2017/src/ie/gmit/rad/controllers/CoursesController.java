package ie.gmit.rad.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ie.gmit.rad.dao.DAO;
import ie.gmit.rad.models.Course;

@ManagedBean
public class CoursesController {
	private ArrayList<Course> courses;
	private DAO dao;
	
	// Constructors
	public CoursesController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the courses from the database and store them in an instance variable
	public void loadCourses() {
		try {
			courses = dao.getCourses();
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
	
	// Use the DAO to add the new course to the database
	public String addCourse(Course course) {
		try {
			dao.addCourse(course);
			return "list_courses";
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
	
	// Getters and Setters
	public ArrayList<Course> getCourses() {
		return courses;
	}
}
