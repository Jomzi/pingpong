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
import ie.gmit.rad.models.College;

@ManagedBean
@SessionScoped
public class CollegesController {
	private ArrayList<College> colleges;
	private DAO dao;
	
	// Constructors
	public CollegesController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the colleges from the database and store in an instance variable
	public void loadColleges() {
		try {
			colleges = dao.getColleges();
			System.out.println(colleges + " TEST");
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
	
	// Use the DAO to delete the given college from the database
	public String deleteCollege(College college) {
		try {
			dao.deleteCollege(college);
			colleges.remove(college);
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
	
	// Use the DAO to add the new college to the database
	public String addCollege(College college) {
		try {
			dao.addCollege(college);
			return "list_colleges";
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
	
	// Use the DAO to update a college in the database
	public String updateCollege(College college) {
		try {
			dao.updateCollege(college);
			return "list_colleges";
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
	
	// Add the selected college to ExternalContext 
	public String setUpdateCollege(College college) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("college",college);
		
		return "update_college";
	}
	
	// Get the college from the ExternalContext
	public College getUpdateCollege() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		College college = (College)sessionMap.get("college");
		
		return college;
	}
	
	// Getters and Setters
	public ArrayList<College> getColleges() {
		return colleges;
	}
}
