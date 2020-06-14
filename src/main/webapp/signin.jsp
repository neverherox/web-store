<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>sign in</title>
    <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="header">Sign in to Web Store</div>
<div class="blok-center">
<form method="POST" action="sign_in">
    <table>
        <tr>
            <td colspan="2">Sign in to the WebStore application:</td>
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
            <td>
                <input type="submit" value="Sign in"/>
                &nbsp;<a href="sign_up">Sign up</a>
            </td>
        <tr>
    </table>
    <p style="color:darkblue">
        <strong>Tip:</strong> &nbsp;Administrator's login: <em>root</em> &nbsp;password: <em>1234</em>
    </p>
</form>
</div>
</body>
</html>
