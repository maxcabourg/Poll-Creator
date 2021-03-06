<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login</title>

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
  	<jsp:include page="../views/nav/visitorNav.jsp" />
    <form method="POST" action="${pageContext.request.contextPath}/login">
    	<div class="form-group">
		    <label for="pseudo">Pseudo : </label>
		    <input type="text" class="form-control" name="pseudo" placeholder="Pseudo">
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Password : </label>
		    <input type="password" class="form-control" name="password" placeholder="Password">
  		</div>
  		<button type="submit" class="btn btn-success">Submit</button>
  		<c:if test="${not empty error}">
			    <div class="alert alert-danger" role="alert">
					${error}
				</div>
		</c:if>
    </form>
    <jsp:include page="../views/footer.jsp" />

  </body>
</html>