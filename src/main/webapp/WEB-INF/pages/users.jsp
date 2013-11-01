<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="container">

    <div class="page-header">
        <h1>Users Administration <small>The fast and easy way.</small></h1>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Users Fast Administration</div>

        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>email</th>
                <th>name</th>
                <th>surname</th>
                <th>password</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${entities}" var="user">
                <tr class="element" id="${user.email}">
                    <td>${user.email}</td>
                    <td class="name"><p class="edit">${user.name}</p></td>
                    <td class="surname"><p class="edit">${user.surname}</p></td>
                    <td class="password"><p class="edit">${user.password}</p></td>
                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus" id="0">
                <td class="email"><p class="edit">email</p></td>
                <td class="name"><p class="edit">name</p></td>
                <td class="surname"><p class="edit">surname</p></td>
                <td class="password"><p class="edit">password</p></td>
                <td><a href="#"><span class="glyphicon glyphicon-plus-sign"></span></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<script src="<c:url value="/resources/assets/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.jeditable.mini.js"/>"></script>
<script type="text/javascript">
    var baseUrl = "/users";
</script>

<script src="<c:url value="/resources/js/ajax-notbooks.js"/>"></script>
<jsp:include page="includes/footer.jsp" />