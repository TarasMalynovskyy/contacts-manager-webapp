<%@ page import="com.ivvysoft.cmw.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="<c:url value="/main" />">Back to main page</a>
	<br>
	<br>
	<br>
	<c:forEach var="tempPerson" items="${persons}">
		ID: ${tempPerson.id} <br>
		First Name: ${tempPerson.firstName}<br>
		Last Name: ${tempPerson.lastName}<br>
		Phone: ${tempPerson.phone}<br>
		Email: ${tempPerson.email}<br>
		<br>
	</c:forEach>
</body>
</html>