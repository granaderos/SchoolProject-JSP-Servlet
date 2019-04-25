<!DOCTYPE html>
<%@page import="com.mare.domain.Employee"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">
<head>
   	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Administration</title>
</head>
<body style="margin-top: 50px;">
	
	<div class="container container-fluid">
		<jsp:include page="sidebar_menu.jsp" />
		
		<div class="main">
    		<h3>Edit Employee</h3>
       		<form id="form_employee" method="POST" action="/SchoolProject/EditEmployee">
       			<% Employee emp = (Employee) session.getAttribute("employee"); %>
       			<input type="hidden" value="<%= emp.getId() %>" name="id" id="id" />
       			<label for="name">Name</label>
       			<input type="text" value="<%= emp.getName() %>" class="form-control" name="name" id="name" />
       			<label for="age">Age</label>
       			<input type="text" value="<%= emp.getAge() %>" class="form-control" name="age" id="age" />
       			<label for="department">Department</label>
       			<select class="form-control" name="department" id="department">
       				<% ArrayList<String> departmentList = (ArrayList<String>) session.getAttribute("departments"); 
						for (String department: departmentList) {  
							String selected = "";
							if(department.equals(emp.getDepartment())) selected = "selected"; %>
				    			<option <%= selected %> value="<% out.print(department); %>"><% out.print(department); %></option>
			   		<%  } %>
       			</select>
       			<label for="salary">Salary</label>
       			<input type="text" value="<%= emp.getSalary() %>" class="form-control" name="salary" id="salary" />
       			<label for="role">Role</label>
       			<select class="form-control" name="role" id="role">
       				<% ArrayList<String> roleList = (ArrayList<String>) session.getAttribute("roles"); 
					for (String role: roleList) {  
						String selected = "";
						if(role.equals(emp.getRole())) selected = "selected"; %> 
				    	<option <%= selected %> value="<% out.print(role); %>"><% out.print(role); %></option>
			   		<% } %>
       			</select>
       			<br>
       			<button class="btn btn-block btn-outline-primary">Save</button>
       		</form>
       	</div>
      </div>
</body>
</html> 