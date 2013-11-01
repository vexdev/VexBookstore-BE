<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="container">

    <div class="page-header">
        <h1>Administrators Administration <small>The fast and easy way.</small></h1>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Admins Fast Administration</div>

        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>email</th>
                <th>password</th>
                <th>name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${entities}" var="admin">
                <tr class="element" id="${admin.email}">
                    <td>${admin.email}</td>
                    <td class="password"><p class="edit">${admin.password}</p></td>
                    <td class="name"><p class="edit">${admin.displayName}</p></td>
                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus" id="0">
                <td class="email"><p class="edit">email</p></td>
                <td class="password"><p class="edit">password</p></td>
                <td class="name"><p class="edit">display name</p></td>
                <td><a href="#"><span class="glyphicon glyphicon-plus-sign"></span></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<script src="<c:url value="/resources/assets/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.jeditable.mini.js"/>"></script>
<script type="text/javascript">
    var baseUrl = "/administrators";
</script>

<script src="<c:url value="/resources/js/ajax-notbooks.js"/>"></script>
<jsp:include page="includes/footer.jsp" />