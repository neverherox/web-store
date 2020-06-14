<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>user</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Welcome ${user.login} &nbsp;<a href="log_out">(logout)</a></div>
<div class="sidebar">
    Products<br>
    <a href="view_user_orders?id=${user.id}">Your orders</a>
</div>
<div class = "content">
        <h2 style = "color:#6699ff">Products</h2>
            <c:forEach items="${products}" var="product" varStatus="status">
            <div class = "element">
                <img src ="${product.image}"/><br>
                <div class = "description">
                ${product.name}<br>
                ${product.price}<br>
                ${catalogs[status.index].name}<br>
                ${product.description}<br>
                    <form method="POST" action="add_order">
                        <input type = "hidden" name = "add" value = "${product.id}">
                        <input type="submit" value="add" name = "addButton"/>
                        <input type = "hidden" name = "userId" value = "${user.id}">
                    </form>
                </div>
            </div>
        </c:forEach>
</div>
</body>
</html>