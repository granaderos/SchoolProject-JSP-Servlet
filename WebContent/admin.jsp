<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<html lang="en">
<head>
    <!--<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    -->
    <link rel="stylesheet" type="text/css" href="css/style.css" />


	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Administration</title>
</head>
<body style="margin-top: 50px;">
	
	<div class="container container-fluid">
		<jsp:include page="sidebar_menu.jsp" />
		
		<div class="main">
       		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#div_employee">Add Employee</a></li>
				<li><a data-toggle="tab" href="#div_student">Add Student</a></li>
				<li><a data-toggle="tab" href="#div_course">Add Course</a></li>
				<li><a data-toggle="tab" href="#div_section">Add Section</a></li>
				<li><a data-toggle="tab" href="#div_department">Add Department</a></li>
				<li><a data-toggle="tab" href="#div_role">Add Role</a></li>
			</ul>
			
			<div class="tab-content">
				<!-- Forms  -->
	       		<div id="div_employee" class="tab-pane fade in active">
	       			<h3>Employee Form</h3>
		       		<form id="form_employee" method="POST" action="/SchoolProject/AddEmployee">
		       			<label for="name">Name</label>
		       			<input type="text" class="form-control" name="name" id="name" />
		       			<label for="age">Age</label>
		       			<input type="text" class="form-control" name="age" id="age" />
		       			<label for="department">Department</label>
		       			<select class="form-control" name="department" id="department">
		       				<% ArrayList<String> departmentList = (ArrayList<String>) session.getAttribute("departments"); 
								for (String department: departmentList) {  %> 
						    		<option value="<% out.print(department); %>"><% out.print(department); %></option>
					   		<%  } %>
		       			</select>
		       			<label for="salary">Salary</label>
		       			<input type="text" class="form-control" name="salary" id="salary" />
		       			<label for="role">Role</label>
		       			<select class="form-control" name="role" id="role">
		       				<% ArrayList<String> roleList = (ArrayList<String>) session.getAttribute("roles"); 
							for (String course: roleList) {  %> 
						    	<option value="<% out.print(course); %>"><% out.print(course); %></option>
					   		<% } %>
		       			</select>
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Employee</button>
		       		</form>
	       		</div>
	       		
	       		<div id="div_student" class="tab-pane fade">
	       			<h3>Student Form</h3>
		       		<form id="form_student" method="POST" action="/SchoolProject/AddStudent">
		       			<label for="name">Name</label>
		       			<input type="text" class="form-control" name="name" id="name" />
		       			<label for="age">Age</label>
		       			<input type="text" class="form-control" name="age" id="age" />
		       			<label for="course">Course</label>
		       			<select class="form-control" name="course" id="course">
		       				<% ArrayList<String> courseList = (ArrayList<String>) session.getAttribute("courses"); 
								for (String course: courseList ) {  %> 
						    		<option value="<% out.print(course); %>"><% out.print(course); %></option>
					   		<%  } %>
		       			</select>
		       			<label for="section">Section</label>
		       			<select class="form-control" name="section" id="section">
		       				<% ArrayList<String> sectionList = (ArrayList<String>) session.getAttribute("sections"); 
								for (String section: sectionList ) {  %> 
						    		<option value="<% out.print(section); %>"><% out.print(section); %></option>
					   		<%  } %>
		       			</select>
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Student</button>
		       		</form>
	       		</div>
				
				<div id="div_course" class="tab-pane fade">
	       			<h3>Course Form</h3>
		       		<form id="form_course" method="POST" action="/SchoolProject/AddCourse">
		       			<label for="course">Course</label>
		       			<input type="text" class="form-control" name="course" id="course" />
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Course</button>
		       		</form>
	       		</div>
	       		       		
	       		<div id="div_department" class="tab-pane fade">
	       			<h3>Department Form</h3>
		       		<form id="form_department" method="POST" action="/SchoolProject/AddDepartment">
		       			<label for="department">Department</label>
		       			<input type="text" class="form-control" name="department" id="department" />
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Department</button>
		       		</form>
	       		</div>
	       		
	       		<div id="div_role" class="tab-pane fade">
	       			<h3>Role Form</h3>
		       		<form id="form_role" method="POST" action="/SchoolProject/AddRole">
		       			<label for="role">Role</label>
		       			<input type="text" class="form-control" name="role" id="role" />
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Role</button>
		       		</form>
	       		</div>
	       		
	       		<div id="div_section" class="tab-pane fade">
	       			<h3>Section Form</h3>
		       		<form id="form_section" method="POST" action="/SchoolProject/AddSection">
		       			<label for="section">Section</label>
		       			<input type="text" class="form-control" name="section" id="section" />
		       			<br>
		       			<button class="btn btn-block btn-outline-primary">Submit Section</button>
		       		</form>
	       		</div>
	       		
			</div>  
		</div>
    </div>
</body>
</html> 