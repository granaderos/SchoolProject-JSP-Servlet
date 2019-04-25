package com.mare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mare.controller.Controller;

/**
 * Servlet implementation class DeletePerson
 */
@WebServlet("/DeletePerson")
public class DeletePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePerson() {
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
		String type = request.getParameter("type");
		
		System.out.println("ID to delete = " + id);
		System.out.println("Type to delete = " + type);
		
		Controller controller = new Controller();
		boolean successful = controller.deletePerson(id, type);
		if(successful) {
			if(type.equals("student"))
				response.sendRedirect("http://localhost:8080/SchoolProject/DisplayStudents");
			else if(type.equals("employee"))
				response.sendRedirect("http://localhost:8080/SchoolProject/DisplayEmployees");
		} else {
			// do something deletion failed;
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
 