<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>checkout</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<form action="checkout_order">
    <input type = "hidden" name = "orderId" value = "${order.id}">
    <input type = "hidden" name = "userId" value = "${user.id}">
    <input type = "hidden" name = "productId" value = "${product.id}">
    <div class="header">Checkout</div>
<div class="content">
    <div class = "element">
            <img src="https://st5.prosto.im/cache/st5/1/0/3/4/1034/1034_230x.jpg"><br>
        Card number(16 digits):<br>
                <input required type="text" name="card_number"/><br>
            Validity:<br>
                <input type="date" name="validity"/><br>
            CVV (3 digits):<br>
                <input required type="text" name="cvv"/><br><br>
                <input type="submit" value="checkout"/><br><br>
                <button type="button" onclick="window.history.back()">cancel</button><br><br>
        <c:if test="${exception!=null}">
            <p style="color:red;">
                    ${exception}
            </p>
        </c:if>
    </div>
</div>
</form>
</body>
</html>