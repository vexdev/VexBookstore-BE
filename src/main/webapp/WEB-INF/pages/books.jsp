<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="container">

    <div class="page-header">
        <h1>Books Administration <small>The fast and easy way.</small></h1>
    </div>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Books Fast Administration</div>

        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>isbn</th>
                <th>name</th>
                <th style="width: 20%">description</th>
                <th>file</th>
                <th>price</th>
                <th>pages</th>
                <th>ed.</th>
                <th>authors</th>
                <th>categories</th>
                <th>editors</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td><p class="edit" id="isbn_${book.isbn}">${book.isbn}</p></td>
                    <td><p class="edit" id="name_${book.isbn}">${book.name}</p></td>
                    <td><p class="edit" id="desc_${book.isbn}">${book.description}</p></td>
                    <td>
                        <a href=""><span class="glyphicon glyphicon-download"></span></a>
                        <a href=""><span class="glyphicon glyphicon-edit"></span></a>
                        <a href=""><span class="glyphicon glyphicon-remove"></span></a>
                    </td>
                    <td>${book.price}฿</td>
                    <td>${book.pages}</td>
                    <td>${book.edition}</td>
                    <td>
                        <c:forEach items="${book.authors}" var="author">
                            <span><a href=""><span class="glyphicon glyphicon-minus"></span></a> ${author.name} ${author.surname}</span><br />
                        </c:forEach>
                        <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                    </td>
                    <td>
                        <c:forEach items="${book.categories}" var="category">
                            <span><a href=""><span class="glyphicon glyphicon-minus"></span></a> ${category.name}</span><br />
                        </c:forEach>
                        <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                    </td>
                    <td>
                        <c:forEach items="${book.editors}" var="editor">
                            <span><a href=""><span class="glyphicon glyphicon-minus"></span></a> ${editor.name}</span><br />
                        </c:forEach>
                        <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                    </td>
                    <td>
                        <a href=""><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
            <tr class="plus">
                <td>0000000000</td>
                <td>I am a book</td>
                <td>This is a short description ...</td>
                <td>
                    <a href=""><span class="glyphicon glyphicon-import"></span></a>
                </td>
                <td>0.00$</td>
                <td>000</td>
                <td>0</td>
                <td>
                    <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                </td>
                <td>
                    <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                </td>
                <td>
                    <span><a href=""><span class="glyphicon glyphicon-plus"></span></a></span>
                </td>
                <td>
                    <a href=""><span class="glyphicon glyphicon-plus-sign"></span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<script src="<c:url value="/resources/assets/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.jeditable.mini.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.edit').editable(function(value, settings) {
            var url = '/books/storeParameter';
            var completeID = $(this).attr('id');
            var splitted = completeID.split("_");
            var data = {
                id    : splitted[1],
                field : splitted[0],
                value : value
            }
            $.getJSON(url, data, function(result) {});
            return(value);
        }, {
            indicator : 'Saving...',
            tooltip   : 'Click to edit...'
        });
    });
</script>

<jsp:include page="includes/footer.jsp" />