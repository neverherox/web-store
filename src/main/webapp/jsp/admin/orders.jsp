<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>orders</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">Orders</div>
<div class="sidebar">
    <a href="admin">Products</a><br>
    Orders
</div>
<div class="content">
    <table>
        <tr>
            <td>
                <table>
                    <tr>
                        <th>Buyer id</th>
                        <th>Buyer name</th>
                        <th>Order id</th>
                        <th>Product</th>
                        <th>Order status</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <c:forEach items="${user.order.products}" var="product">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>${user.order.id}</td>
                                <td>${product.name}</td>
                                <td>${user.order.status}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

