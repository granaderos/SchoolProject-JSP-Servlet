package com.mare.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mare.controller.Controller;
import com.mare.domain.Student;

/**
 * Servlet implementation class DisplayOptions
 */
@WebServlet("/DisplayOptions")
public class DisplayOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller controller = new Controller();
		ArrayList<String> courses = controller.getCourses();
		ArrayList<String> departments = controller.getDepartments();
		ArrayList<String> roles = controller.getRoles();
		ArrayList<String> sections = controller.getSections();
		
		request.getSession().setAttribute("courses", courses);
		request.getSession().setAttribute("departments", departments);
		request.getSession().setAttribute("roles", roles);
		request.getSession().setAttribute("sections", sections);
		
		response.sendRedirect("admin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
