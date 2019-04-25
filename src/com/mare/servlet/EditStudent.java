package com.mare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mare.controller.Controller;
import com.mare.domain.Person;
import com.mare.domain.Student;

/**
 * Servlet implementation class EditStudent
 */
@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		System.out.println("NEW ========= NAME = " + name);
		int age = Integer.parseInt(request.getParameter("age"));
		String course = request.getParameter("course");
		String section = request.getParameter("section");
		
		Person person = new Student();
		person.setId(id);
		person.setName(name);
		person.setAge(age);
		 ((Student) person).setCourse(course);
		 ((Student) person).setSection(section);
		 
		 Controller controller = new Controller();
		 boolean successful = controller.editPerson(person);
		 
		if(successful) {
			response.sendRedirect("http://localhost:8080/SchoolProject/DisplayStudents");
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
