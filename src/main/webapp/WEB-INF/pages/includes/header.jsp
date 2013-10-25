<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="<c:url value="/resources/assets/ico/favicon.png"/>">

    <title>Vex Bookstore ... loaded</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/dist/css/bootstrap.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/admin.css"/>" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/assets/js/html5shiv.js"/>"></script>
    <script src="<c:url value="/resources/assets/js/respond.min.js"/>"></script>
    <![endif]-->
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Vex's Bookstore</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="admin">Home</a></li>
                <li><a href="books">Books</a></li>
                <li><a href="authors">Authors</a></li>
                <li><a href="categories">Categories</a></li>
                <li><a href="editors">Editors</a></li>
                <li><a href="users">Users</a></li>
                <li><a href="administrators">Administrators</a></li>
                <li class="active"><a>Logged in as ${userDetails.username}</a></li>
            </ul>
        </div><!--/.navbar-collapse -->
    </div>
</div>