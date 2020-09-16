<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>sign in</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">Sign in to Web Store</div>

<div class="blok-center">

    <form method="POST" action="sign_in">

        <div class="table">
            Sign in to the WebStore application:<br>

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

            <input type="submit" value="Sign in"/>&nbsp;<a href="sign_up">Sign up</a>
        </div>
        <p style="color:darkblue">
            <strong>Tip:</strong> &nbsp;Administrator's login: <em>root</em> &nbsp;password: <em>1234</em>
        </p>

    </form>

</div>

</body>
</html>
