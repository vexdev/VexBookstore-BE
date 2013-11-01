<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Luca Vitucci">
    <link rel="shortcut icon" href="<c:url value="/resources/assets/ico/favicon.png"/>">

    <title>Vex Bookstore Administration</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/dist/css/bootstrap.css"/>" rel="stylesheet">

    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
</head>

<body>

<div class="container">

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form name="f" action="<c:url value="j_spring_security_check" />" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input name="j_username" type="text" class="form-control" placeholder="Email address" required autofocus>
        <input name="j_password" type="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>