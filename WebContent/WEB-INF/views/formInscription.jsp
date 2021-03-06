<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Register</title>

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
    <form method="POST" action="${pageContext.request.contextPath}/inscription">
    	<div class="form-group">
		    <label for="pseudo">Pseudo : </label> It must contain at least 2 characters and 20 characters maximum
		    <input type="text" class="form-control" name="pseudo" placeholder="Pseudo">
		    <c:if test="${not empty errors.pseudoLength}">
			    <div class="alert alert-danger" role="alert">
					${errors.pseudoLength}
				</div>
			</c:if>
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Password : </label> It must contain at least one number, one character in UPPER case, one in lower case and 1 special character among @#$!% and must have between 6 and 40 characters
		    <input type="password" class="form-control" name="password">
		   <c:if test="${not empty errors.passwordValidity}">
			    <div class="alert alert-danger" role="alert">
					${errors.passwordValidity}
				</div>
			</c:if>
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Confirm password : </label>
		    <input type="password" class="form-control" name="passwordConfirm">
		    <c:if test="${not empty errors.passwordConfirm}">
			    <div class="alert alert-danger" role="alert">
					${errors.passwordConfirm}
				</div>
			</c:if>
  		</div>
  		<div class="form-group">
		    <label for="mail"> Email : </label>
		    <input type="email" class="form-control" name="mail" placeholder="Email">
		    <c:if test="${not empty errors.mailValidity}">
			    <div class="alert alert-danger" role="alert">
					${errors.mailValidity}
				</div>
			</c:if>
			<c:if test="${not empty errors.mailLength}">
			    <div class="alert alert-danger" role="alert">
					${errors.mailLength}
				</div>
			</c:if>
  		</div>
  		<c:if test="${not empty error}">
			    <div class="alert alert-danger" role="alert">
					${error}
				</div>
		</c:if>
  		<button type="submit" class="btn btn-success">Submit</button>
    </form>
    <jsp:include page="../views/footer.jsp" />

  </body>
</html>