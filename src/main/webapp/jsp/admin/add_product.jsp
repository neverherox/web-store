<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>add</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<form method="POST" action="add_product">
    <table>
        <tr>
            <th>Catalog id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
        </tr>
        <tr>
            <td><input required type="text" name="catalogId"/></td>
            <td><input required type="text" name="name"/></td>
            <td><input required type="text" name="price"/></td>
            <td><input required type="text" name="description"/></td>
        </tr>
    </table>
    <input type="submit" value="add"/>&nbsp;
    <button type="button" onclick="window.history.back()">cancel</button>
</form>
</body>
</html>