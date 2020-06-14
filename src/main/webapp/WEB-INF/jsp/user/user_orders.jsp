<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>orders</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Your orders</div>
<div class="sidebar">
    <a href="user">Products</a><br>
    Your orders
</div>
<div class = "content">
    <table>
        <tr>
            <td>
                <table>
                    <tr>
                <th>Order status</th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.status}</td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <table>
                    <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Catalog</th>
                <th>Description</th>
                <th>Checkout</th>
                <th>Delete</th>
                    </tr>
            <c:forEach items="${products}" var="product" varStatus="status">
            <tr>
                <td>${product.name}</></td>
                <td>${product.price}</td>
                <td>${catalogs[status.index].name}</td>
                <td>${product.description}</td>
                <td>
                    <form method="POST" action="checkout_order">
                        <input type = "hidden" name = "orderId" value = "${orders[status.index].id}">
                        <input type = "hidden" name = "userId" value = "${user.id}">
                    <input type = "hidden" name = "checkout" value = "${product.id}">
                    <input type="submit" value="checkout"  name = "checkoutButton"/>
                    </form>
                </td>
                <td>
                    <form method="POST" action="delete_order">
                        <input type = "hidden" name = "orderId" value = "${orders[status.index].id}">
                        <input type = "hidden" name = "userId" value = "${user.id}">
                    <input type = "hidden" name = "delete" value = "${product.id}">
                    <input type="submit" value="delete"name = "deleteButton"/>
                    </form>
                </td>
            </tr>
            </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>