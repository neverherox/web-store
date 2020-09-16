<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>sign up</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">Sign up to Web Store</div>
<div class="blok-center">
    <form method="POST" action="sign_up">
        <div class="table">
            Sign up to the WebStore application:<br>
            <div class="table-element">
                <div class="label">
                    Login:
                </div>
                <input required type="text" name="login"/>
            </div>

            <div class="table-element">
                <div class="label">
                    Password:
                </div>
                <input required type="password" name="password"/>
            </div>
            <div class="table-element">
                <div class="label">
                    Retype:
                </div>
                <input required type="password" name="retype_password"/>
            </div>
            <input type="submit" value="Sign up"/>&nbsp;<a href="sign_in">Sign in</a>

            <c:if test="${exception!=null}">
            <p style="color:red;">
                    ${exception}
            </p>
            </c:if>
    </form>
</div>
</body>
</html>
