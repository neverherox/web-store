<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>admin</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Welcome ${user.login} &nbsp;<a href="log_out">(logout)</a></div>
<div class="sidebar">
    Products<br>
    <a href="orders">Orders</a>
</div>
<div class = "content">
    <form method="POST" action="admin">
        <input type="submit" value="add" name = "add"/>
    </form>

    <c:forEach items="${products}" var="product" varStatus="status">
        <div class = "element">
            <img src="${product.image}"/><br>
            <div class = "description">
            ${product.name}<br>
            ${product.price}<br>
            ${catalogs[status.index].name}<br>
            ${product.description}<br>

            <form action="edit_product">
            <input type="submit" value="edit" name = "editButton"/>
            <input type = "hidden" name = "edit" value = "${product.id}">
            </form>

            <form method="POST" action="delete_product">
            <input type="submit" value="delete" name = "deleteButton"/>
            <input type = "hidden" name = "delete" value = "${product.id}">
            </form>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>