package ie.gmit.rad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ie.gmit.rad.models.College;
import ie.gmit.rad.models.Course;
import ie.gmit.rad.models.Student;

public class DAO {
	private DataSource mysqlDS;
	
	// Constructors
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/collegedb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	// Return an array list of all colleges from the database
	public ArrayList<College> getColleges() throws Exception {
		ArrayList<College> colleges = new ArrayList<College>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * " +
					 									"FROM college");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String colID = rs.getString("colID");
			String colName = rs.getString("colName");
			String colAddr = rs.getString("colAddr");
			colleges.add(new College(colID, colName, colAddr));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return colleges;
	}
	
	// Delete the given college from the college table
	public void deleteCollege(College college) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM college " +
					 									"WHERE colID=?");
		
		myStmt.setString(1, college.getColId());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Insert a new college into the college table
	public void addCollege(College newCollege) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO college " +
					 									"VALUES (?,?,?)");
		
		myStmt.setString(1, newCollege.getColId());
		myStmt.setString(2, newCollege.getColName());
		myStmt.setString(3, newCollege.getColAddr());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Update the college in the college table with the code of the given college
	public void updateCollege(College college) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("UPDATE colege " +
					 									"SET colName=?, " +
					 									"colAddr=? " +
					 									"WHERE colID=?;");
		
		myStmt.setString(1, college.getColName());
		myStmt.setString(2, college.getColAddr());
		myStmt.setString(3, college.getColId());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Return an array list of all courses from the database
	public ArrayList<Course> getCourses() throws Exception {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * " +
					 									"FROM course");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String colID = rs.getString("colID");
			College college = new College(colID);
			
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			courses.add(new Course(college, cID, cName));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return courses;
	}
	
	// Insert a new course into the course table
	public void addCourse(Course newCourse) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO course " +
					 									"VALUES (?,?,?)");
		
		myStmt.setString(1, newCourse.getCollege().getColId());
		myStmt.setString(2, newCourse.getCId());
		myStmt.setString(3, newCourse.getCName());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Return an array list of all students from the database
	public ArrayList<Student> getStudents() throws Exception {
		ArrayList<Student> students = new ArrayList<Student>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM college ma " +
														"INNER JOIN course mo " +
														"ON ma.colID = mo.colID " +
														"INNER JOIN student v " +
														"ON mo.cID = v.cID;");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String colID = rs.getString("colID");
			String colName = rs.getString("colName");
			String colAddr = rs.getString("colAddr");
			College college = new College(colID, colName, colAddr);
			
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			Course course = new Course(college, cID, cName);
			
			String sID = rs.getString("sid");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String address = rs.getString("address");
			students.add(new Student(sID,course, first_name,last_name, address));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return students;
	}
	
	// Insert a new student into the student table
	public void addStudent(Student newStudent) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO student " +
					 									"VALUES (?,?,?,?,?,?)");
		
		myStmt.setString(1, newStudent.getSId());
		myStmt.setString(2, newStudent.getCourse().getCollege().getColId());
		myStmt.setString(3, newStudent.getCourse().getCId());
		myStmt.setString(4, newStudent.getFirstName());
		myStmt.setString(5, newStudent.getLastName());
		myStmt.setString(6, newStudent.getAddress());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	

}
