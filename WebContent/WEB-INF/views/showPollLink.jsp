<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Broadcast your poll !</title>

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
	<p>Congratulations ! Now share your poll by clicking this link then give the URL : <a href="${pageContext.request.contextPath}/poll/get?id=${idPoll}">${pageContext.request.contextPath}/poll/get?id=${idPoll}</a></p>
	<jsp:include page="../views/footer.jsp" />
</body>
</html>