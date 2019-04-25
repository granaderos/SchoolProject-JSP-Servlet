package com.mare.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mare.domain.Employee;
import com.mare.domain.Person;
import com.mare.domain.Student;

public class Controller {
	
	public Person authenticate(String username, String password) {
		Dao dao = new Dao();
		Person person = null;
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			String sql = "SELECT * FROM person WHERE username = ? AND password = password(?) AND type='admin'";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			
			boolean exist = false;
			while(rs.next()) {
				if(!exist) {
					exist = true;
					person = new Person(); 	
				}
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				
			}
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {}
		}
		return person;
	}
	
	@SuppressWarnings("resource")
	public boolean addPerson(Person person) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			
			String type = "admin";
			if(person instanceof Employee) type = "employee";
			else if(person instanceof Student) type = "student";
			
			String sql_person = "INSERT INTO person VALUES (null, ?, ?, null, null, ?);";
			psmt = connection.prepareStatement(sql_person, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, person.getName());
			psmt.setInt(2, person.getAge());
			psmt.setString(3, type);
			psmt.executeUpdate();
			
			rs = psmt.getGeneratedKeys();
			
			int person_id = 0;
			if(rs.next()){
				person_id = rs.getInt(1);
			}
			
			if(person instanceof Employee) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM department WHERE department = '" + ((Employee) person).getDepartment() + "';");

				int department_id = 0;
				while(rs.next()) {
					department_id = rs.getInt("id");
				}
				
				// getting role_id
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM role WHERE role = '" + ((Employee) person).getRole() + "';");

				int role_id = 0;
				while(rs.next()) {
					role_id = rs.getInt("id");
				}
				
				// Add other details to employee table
				String sql_employee= "INSERT INTO employee VALUES (null, ?, ?, ?, ?);";
				psmt = connection.prepareStatement(sql_employee);
				psmt.setInt(1, person_id);
				psmt.setInt(2, department_id);
				psmt.setDouble(3, ((Employee) person).getSalary());
				psmt.setInt(4, role_id);
				psmt.executeUpdate();
				
			} else if(person instanceof Student) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM course WHERE course = '" + ((Student) person).getCourse() + "';");

				int course_id = 0;
				while(rs.next()) {
					course_id = rs.getInt("id");
				}
				
				// getting role_id
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM section WHERE section = '" + ((Student) person).getSection() + "';");

				int section_id = 0;
				while(rs.next()) {
					section_id = rs.getInt("id");
				}
				
				// Add other details to student table
				String sql_student= "INSERT INTO student VALUES (null, ?, ?, ?);";
				psmt = connection.prepareStatement(sql_student);
				psmt.setInt(1, person_id);
				psmt.setInt(2, course_id);
				psmt.setInt(3, section_id);
				psmt.executeUpdate();
			}
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {}
		}
		
		return successful;
	}
	
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * FROM person as p, employee as e, department as d, role as r "
										  + "WHERE p.id = e.person_id AND e.department_id = d.id AND e.role_id = r.id ORDER BY p.id DESC;");
			
			Employee emp = null;
			
			while(rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setDepartment(rs.getString("department"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setRole(rs.getString("role"));
				
				employeeList.add(emp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employeeList;
	}
	
	public ArrayList<Student> getStudents() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("Select * FROM person as p, student as stud, course as c, section as sec "
										  + "WHERE p.id = stud.person_id AND stud.course_id = c.id AND stud.section_id = sec.id ORDER BY p.id DESC;");
			
			Student stud = null;
			
			while(rs.next()) {
				stud = new Student();
				stud.setId(rs.getInt("id"));
				stud.setName(rs.getString("name"));
				stud.setAge(rs.getInt("age"));
				stud.setCourse(rs.getString("course"));
				stud.setSection(rs.getString("section"));
				
				studentList.add(stud);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return studentList;
	}
	
	public boolean addDepartment(String department) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		PreparedStatement psmt = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			String sql = "INSERT INTO department VALUES (null, ?);";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, department);
			psmt.executeUpdate();
			
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return successful;
	}
	
	public boolean addRole(String role) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		PreparedStatement psmt = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			String sql = "INSERT INTO role VALUES (null, ?);";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, role);
			psmt.executeUpdate();
			
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successful;
	}
	
	public boolean addCourse(String course) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		PreparedStatement psmt = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			String sql = "INSERT INTO course VALUES (null, ?);";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, course);
			psmt.executeUpdate();
			
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successful;
	}
	
	public boolean addSection(String section) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		PreparedStatement psmt = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			String sql = "INSERT INTO section VALUES (null, ?);";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, section);
			psmt.executeUpdate();
			
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successful;
	}
	
	public ArrayList<String> getCourses() {
		ArrayList<String> courseList = new ArrayList<String>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM course ORDER BY course;");
			
			
			while(rs.next()) {
				courseList.add(rs.getString("course"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return courseList;
	}
	
	public ArrayList<String> getDepartments() {
		ArrayList<String> departmentList = new ArrayList<String>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM department ORDER BY department;");
			
			while(rs.next()) {
				departmentList.add(rs.getString("department"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return departmentList;
	}
	
	public ArrayList<String> getRoles() {
		ArrayList<String> roleList = new ArrayList<String>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM role ORDER BY role;");
			
			while(rs.next()) {
				roleList.add(rs.getString("role"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return roleList;
	}
	
	public ArrayList<String> getSections() {
		ArrayList<String> sectionList = new ArrayList<String>();
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM section ORDER BY section;");
			
			while(rs.next()) {
				sectionList.add(rs.getString("section"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sectionList;
	}
	
	public Employee getEmployee(int id) {
		Employee emp = null;
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM person p, employee e, department d, role r " 
								+ " WHERE p.id = " + id + " AND p.id = e.person_id AND e.department_id = d.id AND e.role_id = r.id;");
			while(rs.next()) {
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setDepartment(rs.getString("department"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setRole(rs.getString("role"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}
	
	public Student getStudent(int id) {
		Student stud = null;
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM person p, student stud, course c, section sec " 
								+ " WHERE p.id = " + id + " AND p.id = stud.person_id AND stud.course_id = c.id AND stud.section_id = sec.id;");
			while(rs.next()) {
				stud = new Student();
				stud.setId(id);
				stud.setName(rs.getString("name"));
				stud.setAge(rs.getInt("age"));
				stud.setCourse(rs.getString("course"));
				stud.setSection(rs.getString("section"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return stud;
	}
	@SuppressWarnings("resource")
	public boolean editPerson(Person person) {
		boolean successful = false;
		
		Dao dao = new Dao();
		
		Statement stmt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			
			String sql_person = "UPDATE person SET name = ?, age = ? WHERE id = ?;";
			psmt = connection.prepareStatement(sql_person);
			psmt.setString(1, person.getName());
			psmt.setInt(2, person.getAge());
			psmt.setInt(3, person.getId());
			psmt.executeUpdate();
			System.out.println("PERSON ID = " + person.getId());
			if(person instanceof Employee) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM department WHERE department = '" + ((Employee) person).getDepartment() + "';");

				int department_id = 0;
				while(rs.next()) {
					department_id = rs.getInt("id");
				}
				
				// getting role_id
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM role WHERE role = '" + ((Employee) person).getRole() + "';");

				int role_id = 0;
				while(rs.next()) {
					role_id = rs.getInt("id");
				}
				
				// Add other details to employee table
				String sql_employee= "UPDATE employee SET department_id = ?, salary = ?, role_id = ? WHERE person_id = ?;";
				psmt = connection.prepareStatement(sql_employee);
				psmt.setInt(1, department_id);
				psmt.setDouble(2, ((Employee) person).getSalary());
				psmt.setDouble(3, role_id);
				psmt.setInt(4, person.getId());
				psmt.executeUpdate();
				
			} else if(person instanceof Student) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM course WHERE course = '" + ((Student) person).getCourse() + "';");

				int course_id = 0;
				while(rs.next()) {
					course_id = rs.getInt("id");
				}
				
				// getting role_id
				stmt = connection.createStatement();
				rs = stmt.executeQuery("Select id FROM section WHERE section = '" + ((Student) person).getSection() + "';");

				int section_id = 0;
				while(rs.next()) {
					section_id = rs.getInt("id");
				}
				
				// Add other details to student table
				String sql_student= "UPDATE student SET course_id = ?, section_id = ? WHERE person_id = ?;";
				psmt = connection.prepareStatement(sql_student);
				psmt.setInt(1, course_id);
				psmt.setInt(2, section_id);
				psmt.setInt(3, person.getId());
				psmt.executeUpdate();
			}
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {}
		}
		
		return successful;
	}
	
	@SuppressWarnings("resource")
	public boolean deletePerson(int id, String type) {
		boolean successful = false;
		Dao dao = new Dao();
		
		PreparedStatement psmt = null;
		Connection connection = null;
		
		try {
			connection = dao.getConnection();
			
			String sql = "DELETE FROM person WHERE id = ?;";
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
			sql = "DELETE FROM " + type + " WHERE person_id = ?;";
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
			successful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {}
		}
		return successful;
	}

}
