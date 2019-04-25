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
    <title>Administration</title>
</head>
<body style="margin-top: 50px;">
	
	<div class="container container-fluid">
		<jsp:include page="sidebar_menu.jsp" />
		
		<div class="main">
    		<h3>Edit Student's Data</h3>
       		<form id="form_student" method="POST" action="/SchoolProject/EditStudent">
       			<% Student stud = (Student) session.getAttribute("student"); %>
       			<input type="hidden" value="<%= stud.getId() %>" name="id" id="id" />
       			<label for="name">Name</label>
       			<input type="text" value="<%= stud.getName() %>" class="form-control" name="name" id="name" />
       			<label for="age">Age</label>
       			<input type="text" value="<%= stud.getAge() %>" class="form-control" name="age" id="age" />
       			<label for="course">Course</label>
       			<select class="form-control" name="course" id="course">
       				<% ArrayList<String> courseList = (ArrayList<String>) session.getAttribute("courses");
						for (String course: courseList ) {  
							String selected = "";
       						if(stud.getCourse().equals(course)) selected = "selected"; %> 
				    		<option <%= selected %> value="<% out.print(course); %>"><% out.print(course); %></option>
			   		<%  } %>
       			</select>
       			<label for="section">Section</label>
       			<select class="form-control" name="section" id="section">
       				<% ArrayList<String> sectionList = (ArrayList<String>) session.getAttribute("sections"); 
						for (String section: sectionList ) { 
							String selected = "";
       						if(stud.getSection().equals(section)) selected = "selected"; %>  
				    		<option <%= selected %> value="<% out.print(section); %>"><% out.print(section); %></option>
			   		<%  } %>
       			</select>
       			<br>
       			<button class="btn btn-block btn-outline-primary">Save</button>
       		</form>
       	</div>
      </div>
</body>
</html> 