<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>orders</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">Your order</div>
<div class="sidebar">
    <a href="user">Products</a><br>
    Your orders
</div>
<div class="content">
    <table>
        <tr>
            <td>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Catalog</th>
                        <th>Description</th>
                    </tr>
                    <tr>
                        <td>${user.order.status}</td>
                        <c:forEach items="${user.order.products}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.catalog.name}</td>
                        <td>${product.description}</td>
                        <td>
                            <form method="POST" action="delete_product_from_order">
                                <input type="hidden" name="userId" value="${user.id}">
                                <input type="hidden" name="productId" value="${product.id}">
                                <input type="hidden" name="delete" value="${product.id}">
                                <input type="submit" value="delete" name="deleteButton"/>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>