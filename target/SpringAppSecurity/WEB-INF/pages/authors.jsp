<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="container">

    <div class="page-header">
        <h1>Authors Administration <small>The fast and easy way.</small></h1>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Authors Fast Administration</div>

        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>surname</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${authors}" var="author">
                <tr>
                    <td><p>${author.aid}</p></td>
                    <td><p class="edit" id="name_${author.aid}">${author.name}</p></td>
                    <td><p class="edit" id="desc_${author.aid}">${author.surname}</p></td>
                    <td><a href=""><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            <tr class="plus">
                <td>id</td>
                <td>name</td>
                <td>surname</td>
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