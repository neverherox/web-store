<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>edit</title>
    <link rel="stylesheet" href="style1.css">

</head>
<body>
<form method="POST" action="edit_product">
    <input type = "hidden" name = "productId" value = "${product.id}">
<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
    </tr>
        <tr>
            <td> <input required type="text" name="name"/></td>
            <td> <input required type="text" name="price"/></td>
            <td> <input required type="text" name="description"/></td>
        </tr>
</table>
    <input type="submit" value="save"/>&nbsp;
    <button type="button" onclick="window.history.back()">cancel</button>
</form>
</body>
</html>