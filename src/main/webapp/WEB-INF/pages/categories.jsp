<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="container">

    <div class="page-header">
        <h1>Categories Administration <small>The fast and easy way.</small></h1>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Categories Fast Administration</div>

        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>cid</th>
                <th>name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td><p>${category.cid}</p></td>
                    <td><p class="edit" id="name_${category.name}">${category.name}</p></td>
                    <td><a href=""><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus">
                <td>cid</td>
                <td>name</td>
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