<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mare.domain.Employee"%>
<c:import url="/DisplayEmployees" />
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Employees</title>
</head>
<body style="margin-top: 50px;">
    <div class="container container-fluid">
    	<jsp:include page="sidebar_menu.jsp" />
		
		<div class="main">
			<h3>List of Employees</h3>
	        <table id="tbl_employees" class="table table-hover">
	            <thead>
	                <tr>
	                    <th>Name</th>
	                    <th>Age</th>
	                    <th>Department</th>
	                    <th>Salary</th>
	                    <th>Role</th>
	                    <th></th>
	                </tr>
	            </thead>
	            <tbody id="tbody_employees">
				    <% ArrayList<Employee> employeeList = (ArrayList<Employee>) session.getAttribute("employeeList");
						for (Employee emp : employeeList) {  %> 
					    	<tr>
					       		<td><% out.print(emp.getName()); %></td>
					       		<td><% out.print(emp.getAge()); %></td>
					       		<td><% out.print(emp.getDepartment()); %></td>
					       		<td><% out.print(emp.getSalary()); %></td>
					       		<td><% out.print(emp.getRole()); %></td>
					       		<td>
					       			<a title="edit" href="http://localhost:8080/SchoolProject/GetEmployeeDataToEdit?id=<% out.print(emp.getId()); %>"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;
					       			<a title="delete" href="http://localhost:8080/SchoolProject/DeletePerson?id=<% out.print(emp.getId()); %>&type=employee"><i class="fa fa-trash"></i></a>
				       			</td>
					       	</tr>
				   <% } %>
	            </tbody>
	        </table>
		</div>
    </div>
</body>
</html>