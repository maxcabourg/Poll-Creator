<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show your poll</title>
</head>
<body>
	<p>Congratulations ! Now share your poll by giving this link : <a href="${pageContext.request.contextPath}/poll/get?id=${idPoll}">${pageContext.request.contextPath}/poll/get?id=${idPoll}</a></p>
</body>
</html>