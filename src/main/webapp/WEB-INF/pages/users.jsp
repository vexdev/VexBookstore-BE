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
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><p class="edit" id="emai_${user.email}">${user.email}</p></td>
                    <td><p class="edit" id="name_${user.name}">${user.name}</p></td>
                    <td><p class="edit" id="surn_${user.surname}">${user.surname}</p></td>
                    <td><p class="edit" id="pass_${user.password}">${user.password}</p></td>
                    <td><a href=""><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus">
                <td>email</td>
                <td>name</td>
                <td>surname</td>
                <td>password</td>
                <td><a href=""><span class="glyphicon glyphicon-plus-sign"></span></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<script src="<c:url value="/resources/assets/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.jeditable.mini.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.edit').editable('http://www.example.com/save.php', {
            indicator : 'Saving...',
            tooltip   : 'Click to edit...'
        });
    });
</script>

<jsp:include page="includes/footer.jsp" />