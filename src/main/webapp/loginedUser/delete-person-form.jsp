<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h4>Delete person by ID</h4>
	<hr>
	<form action="<c:url value="/deletePerson" />" method="post">
	
		Enter person ID for delete: <input type="text" name="id"><br>
		<br>
		<input type="submit" name="Submit">
		
	</form>
	
	<br><br><br>
	<a href="<c:url value="/main" />">Back to main page</a>
</body>
</html>