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
            <c:forEach items="${admins}" var="admin">
                <tr>
                    <td><p class="edit" id="name_${admin.email}">${admin.email}</p></td>
                    <td><p class="edit" id="name_${admin.password}">${admin.password}</p></td>
                    <td><p class="edit" id="desc_${admin.displayName}">${admin.displayName}</p></td>
                    <td><a href=""><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus">
                <td>email</td>
                <td>password</td>
                <td>display name</td>
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