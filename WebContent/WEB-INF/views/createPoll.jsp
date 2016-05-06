<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <jsp:include page="../views/nav/loggedNav.jsp" />
    <form method="POST" action="">
    	<div class="form-group">
		    <label for="pseudo">Question : *</label>
		    <input type="text" class="form-control" name="question" required>
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Answer 1 : *</label>
		    <input type="text" class="form-control" name="answer1" required>
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Answer 2 : *</label>
		    <input type="text" class="form-control" name="answer2" required>
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Answer 3 : </label>
		    <input type="text" class="form-control" name="answer3">
  		</div>
  		<div class="form-group">
		    <label for="pseudo">Answer 4 : </label>
		    <input type="text" class="form-control" name="answer4">
  		</div>
  		<button type="submit" class="btn btn-default">Create !</button>
    </form>
    <p><strong>*</strong> : required field</p>
    <jsp:include page="../views/footer.jsp" />

  </body>
</html>