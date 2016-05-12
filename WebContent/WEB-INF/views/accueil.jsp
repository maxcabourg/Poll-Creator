<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome</title>

    <!-- Bootstrap -->
    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  	<c:choose>
    	<c:when test="${empty user}"><jsp:include page="../views/nav/visitorNav.jsp" />
    	<p>Hello dear visitor, you are here on a poll creator which will allow you to create polls and broadcast them to your friends !</p>
    	<p>Do not hesitate to <a href="${pageContext.request.contextPath}/inscription">register !</a></c:when>
    	<c:otherwise><jsp:include page="../views/nav/loggedNav.jsp" /><h1>Welcome back <c:out value="${user.pseudo}" /> !</h1></c:otherwise>
	</c:choose>
  	
    
    <jsp:include page="../views/footer.jsp" />

  </body>
</html>