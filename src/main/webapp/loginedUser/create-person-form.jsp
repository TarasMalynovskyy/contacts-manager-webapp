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
	<h4>Add new person</h4>
	<hr>
	<form action="<c:url value="/createPerson" />" method="post">

		First Name: <input type="text" name="firstName"><br>
		<br> Last Name: <input type="text" name="lastName"><br>
		<br> Phone: <input type="text" name="phone"><br>
		<br> Email: <input type="text" name="email"><br>
		<br> <br> <input type="submit" name="Submit">
	</form>

	<br>
	<br>
	<br>
	<a href="<c:url value="/main" />">Back to main page</a>
</body>
</html>