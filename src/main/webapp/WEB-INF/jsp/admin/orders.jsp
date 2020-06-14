<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>orders</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Orders</div>
<div class="sidebar">
    <a href="admin">Products</a><br>
    Orders
</div>
<div class = "content">
    <table>
    <tr>
    <td>
        <table>
            <tr>
                <th>Buyer</th>
                <th>Buyer id</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.login}</td>
                    <td>${user.id}</td>
                </tr>
            </c:forEach>
        </table>
    </td>
    <td>
        <table>
            <tr>
                <th>Buyer id</th>
                <th>Order id</th>
                <th>Product id</th>
                <th>Order status</th>

            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.userId}</td>
                    <td>${order.id}</td>
                    <td>${order.productId}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
        </table>
    </td>
    <td>
        <table>
            <tr>
                <th>Product id</th>
                <th>Product name</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                </tr>
            </c:forEach>
        </table>
    </td>
    </tr>
    </table>
</div>
</body>
</html>

