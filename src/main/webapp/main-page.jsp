<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Hello! Welcome to Contacts Manager!</h2>
	<hr>
	<p>Choose what you want to do:</p>
	<br>
	
	<a href="<c:url value="/showAllPersons" />">Show all persons</a><br>
	<a href="<c:url value="/createPerson" />">Create new person</a><br>
	<a href="<c:url value="/findPersonByName" />">Find person by name</a><br>
	<a href="<c:url value="/findPersonById" />">Edit person</a><br>
	<a href="<c:url value="/deletePerson" />">Delete person</a><br>
	<form action="<c:url value="/exit" />" method="post">
		<input type="submit" value="Logout" name="Submit" />
	</form>
	
</body>
</html>