package com.mare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mare.controller.Controller;
import com.mare.domain.Employee;

/**
 * Servlet implementation class GetEmployeeDataToEdit
 */
@WebServlet("/GetEmployeeDataToEdit")
public class GetEmployeeDataToEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeDataToEdit() {
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
		Controller controller = new Controller();
		Employee emp = controller.getEmployee(id);
		
		request.getSession().setAttribute("employee", emp);
		request.getSession().setAttribute("departments", controller.getDepartments());
		request.getSession().setAttribute("roles", controller.getRoles());
		response.sendRedirect("edit_employee.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
