<%@ page import="java.util.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<h4>Chose your option</h4>

	<form action="<c:url value="/createNewUser" />" method="get">
		<input type="submit" name="Submit" value="Create New User">
	</form>
	<br>
	<form action="<c:url value="/login" />" method="get">
		<input type="submit" name="Submit" value="login">
	</form>

</body>
</html>