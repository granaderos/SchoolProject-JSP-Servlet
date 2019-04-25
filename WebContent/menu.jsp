<%@page import="com.mare.domain.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% Person person = (Person) session.getAttribute("person"); %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main Menu</title>
</head>
<body>
	<div class="container container-fluid">
    	<h3 style="text-align: center;">SCHOOL PROJECT</h3><hr>
    	<p>Welcome <strong><%= person.getName() %></strong>! </p>
    	<br>
    	
    	<p style="text-align: center;">MENU</p>
        <a href="http://localhost:8080/SchoolProject/DisplayEmployees" class="btn btn-block btn-outline-primary">Employees</a>
        <a href="http://localhost:8080/SchoolProject/DisplayStudents" class="btn btn-block btn-outline-primary">Students</a>
        <a href="http://localhost:8080/SchoolProject/DisplayOptions" class="btn btn-block btn-outline-primary">Admin</a>
    </div>
</body>
</html>