<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

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
  	<jsp:include page="../views/nav/visitorNav.jsp" />
    <form method="POST" action="${pageContext.request.contextPath}/inscription">
    	<div class="form-group">
		    <label for="pseudo">Pseudo : </label> It must contain at least 2 characters and 20 characters maximum
		    <input type="text" class="form-control" name="pseudo" placeholder="Pseudo">
			${errors.pseudoLength}
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Password : </label> It must contain one character in UPPER case, one in lower case and 1 special character among @#$!% and must have between 6 and 40 characters
		    <input type="password" class="form-control" name="password" placeholder="Password">
		    ${errors.passwordValidity}
  		</div>
  		<div class="form-group">
		    <label for="mail"> Email : </label>
		    <input type="email" class="form-control" name="mail" placeholder="Email">
		    ${errors.mailValidity}<br>
		    ${errors.mailLength}
  		</div>
  		<button type="submit" class="btn btn-default">Submit</button>
    </form>
    <jsp:include page="../views/footer.jsp" />

  </body>
</html>