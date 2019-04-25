<!DOCTYPE html>
<%@page import="com.mare.domain.Student"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Students</title>
</head>
<body style="margin-top: 50px;">
    <div class="container container-fluid">
    	<jsp:include page="sidebar_menu.jsp" />
		
		<div class="main">
			<h3>List of Students</h3>
	        <table id="tbl_students" class="table table-hover">
	            <thead>
	                <tr>
	                    <th>Name</th>
	                    <th>Age</th>
	                    <th>Course</th>
	                    <th>Section</th>
	                    <th><th>
	                </tr>
	            </thead>
	            <tbody id="tbody_students">
	            	<% ArrayList<Student> studentList = (ArrayList<Student>) session.getAttribute("studentList"); 
						for (Student stud : studentList) {  %> 
					    	<tr>
					       		<td><% out.print(stud.getName()); %></td>
					       		<td><% out.print(stud.getAge()); %></td>
					       		<td><% out.print(stud.getCourse()); %></td>
					       		<td><% out.print(stud.getSection()); %></td>
					       		<td>
					       			<a title="edit" href="http://localhost:8080/SchoolProject/GetStudentDataToEdit?id=<% out.print(stud.getId()); %>"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;
					       			<a title="delete" href="http://localhost:8080/SchoolProject/DeletePerson?id=<% out.print(stud.getId()); %>&type=student"><i class="fa fa-trash"></i></a>
				       			</td>
					       	</tr>
				   <% } %>
	            </tbody>
	        </table>
		</div>
    </div>
</body>
</html>