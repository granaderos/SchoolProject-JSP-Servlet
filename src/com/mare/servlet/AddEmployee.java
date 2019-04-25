package com.mare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mare.controller.Controller;
import com.mare.domain.Employee;
import com.mare.domain.Person;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String department = request.getParameter("department");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String role = request.getParameter("role");
		
		Person person = new Employee();
		person.setName(name);
		person.setAge(age);
		 ((Employee) person).setDepartment(department);
		 ((Employee) person).setSalary(salary);
		 ((Employee) person).setRole(role);
		 
		 Controller controller = new Controller();
		 boolean successful = controller.addPerson(person);
		 
		if(successful) {
			response.sendRedirect("http://localhost:8080/SchoolProject/DisplayEmployees");
		} else {
			// should notify admin of the error
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
