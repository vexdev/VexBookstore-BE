<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                <th class="picfile">picture & file</th>
                <th>name</th>
                <th style="width: 20%">description</th>
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
                <tr class="book" id="${book.isbn}">
                    <td>${book.isbn}</td>
                    <td class="picfile">
                        <a href="#" class="thumbnail">
                            <input class="fileupload" type="file" name="files[]" data-url="/books/addImage/${book.isbn}" />
                            <img src="${book.imgurl}" alt="book cover"/>
                        </a>
                        <a href="#" class="thumbnail">
                            <input class="fileupload" type="file" name="files[]" data-url="/books/addFile/${book.isbn}" />
                            <h4><a href="${book.url}">Book File</a></h4>
                        </a>
                    </td>
                    <td><p class="edit" id="name_${book.isbn}">${book.name}</p></td>
                    <td><p class="edit long" id="desc_${book.isbn}">${book.description}</p></td>
                    <td><p class="edit" id="price_${book.isbn}">${book.price}฿</p></td>
                    <td><p class="edit" id="pages_${book.isbn}">${book.pages}</p></td>
                    <td><p class="edit" id="edition_${book.isbn}">${book.edition}</p></td>
                    <td class="authors">
                        <ul class="list">
                        <c:forEach items="${book.authors}" var="author">
                            <li class="${author.aid}"><a href="#"><span class="glyphicon glyphicon-minus"></span></a><span>${author.name} ${author.surname}</span></li>
                        </c:forEach>
                        </ul>
                        <span class="add edit-authors">Add author</span><br />
                    </td>
                    <td class="categories">
                        <ul class="list">
                        <c:forEach items="${book.categories}" var="category">
                            <li class="${category.cid}"><a href="#"><span class="glyphicon glyphicon-minus"></span></a><span>${category.name}</span></li>
                        </c:forEach>
                        </ul>
                        <span class="add edit-categories">Add category</span><br />
                    </td>
                    <td class="editors">
                        <ul class="list">
                        <c:forEach items="${book.editors}" var="editor">
                            <li class="${editor.eid}"><a href="#"><span class="glyphicon glyphicon-minus"></span></a><span>${editor.name}</span></li>
                        </c:forEach>
                        </ul>
                        <span class="add edit-editors">Add editor</span><br />
                    </td>
                    <td>
                        <a class="drop" href="#"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
            <tr class="plus book" id="0">
                <td><p class="edit" id="isbn_0">0000000000</p></td>
                <td class="picfile">
                    <h3>To upload after insert.</h3>
                </td>
                <td><p class="edit" id="name_0">I am a book</p></td>
                <td><p class="edit" id="desc_0">This is a short description ...</p></td>
                <td><p class="edit" id="price_0">0.00$</p></td>
                <td><p class="edit" id="pages_0">000</p></td>
                <td><p class="edit" id="edition_0">0</p></td>
                <td class="authors">
                    <ul class="list"></ul>
                    <span class="add edit-authors">Add author</span><br />
                </td>
                <td class="categories">
                    <ul class="list"></ul>
                    <span class="add edit-categories">Add category</span><br />
                </td>
                <td class="editors">
                    <ul class="list"></ul>
                    <span class="add edit-editors">Add editor</span><br />
                </td>
                <td>
                    <a class="add_new" href=""><span class="glyphicon glyphicon-plus-sign"></span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<script src="<c:url value="/resources/assets/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui-widget.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.iframe-transport.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.fileupload.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.jeditable.mini.js"/>"></script>
<script type="text/javascript">

    var categories = {
        <c:forEach var="category" items="${categories}">
            '${category.cid}' : '${category.name}',
        </c:forEach>
    };

    var authors = {
        <c:forEach var="author" items="${authors}">
            '${author.aid}' : '${author.name} ${author.surname}',
        </c:forEach>
    };

    var editors = {
        <c:forEach var="editor" items="${editors}">
            '${editor.eid}' : '${editor.name}',
        </c:forEach>
    };

</script>

<script src="<c:url value="/resources/js/ajax.js"/>"></script>
<jsp:include page="includes/footer.jsp" />