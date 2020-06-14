<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>sign up</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Sign up to Web Store</div>
<div class="blok-center">
<form method="POST" action="sign_up">
    <table>
        <tr>
            <td colspan="2">Sign up to the WebStore application:</td>
        </tr>
        <tr>
            <td>Login:<label>
                <input required type="text" name="login"/>
            </label></td>
        </tr>
        <tr>
            <td>Password:<label>
                <input required type="password" name="password"/>
            </label></td>
        </tr>
        <tr>
            <td>Retype password:<label>
                <input required type="password" name="retype_password"/>
            </label></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Sign up"/>&nbsp;<a href="sign_in">Sign in</a>
            </td>
        <tr>
    </table>
    <c:if test="${exception!=null}">
        <p style="color:red;">
                ${exception}
        </p>
    </c:if>
</form>
</div>
</body>
</html>
