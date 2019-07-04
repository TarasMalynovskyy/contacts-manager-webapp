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
	<h2>Register new user</h2>
	<h4>${errorMessage}</h4>
	<form action="<c:url value="/createNewUser" />" method="post">
		User Name: <input type="text" name="userName">
		<br><br>
		Password: <input type="password" name="pass">
		<br><br>
		Repeat password: <input type="password" name="pass2">
		<br><br>
		<input type="submit" name="Submit">		
		
	</form>
</body>
</html>