<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<style>
    ::selection {
        background-color: #b5e2e7;
    }

    ::-moz-selection {
        background-color: #8ac7d8;
    }

    body {
        background: #FFFFFF;
    }

    .container {
        display: -webkit-flex;
        display: flex;
        height: 100%;
    }

    .logbox {
        padding: 10px;
        margin: 50px auto;
        width: 340px;
        background-color: #fff;
        -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
        -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
        box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
    }

    h1 {
        text-align: center;
        font-size: 175%;
        color: #757575;
        font-weight: 300;
    }

    h1, input {
        font-family: "Open Sans", Helvetica, sans-serif;
    }

    .input {
        width: 75%;
        height: 50px;
        display: block;
        margin: 0 auto 15px;
        padding: 0 15px;
        border: none;
        border-bottom: 2px solid #ebebeb;
    }
    .input:focus {
        outline: none;
        border-bottom-color: #3CC !important;
    }
    .input:hover {
        border-bottom-color: #dcdcdc;
    }
    .input:invalid {
        box-shadow: none;
    }

    .pass:-webkit-autofill {
        -webkit-box-shadow: 0 0 0 1000px white inset;
    }

    .inputButton {
        position: relative;
        width: 85%;
        height: 50px;
        display: block;
        margin: 30px auto 30px;
        color:rgba(0,0,0,.9);
    background-color: #f8f9fa!important;
        border: none;
    }
    .inputButton:active {
        top: 5px;
        box-shadow: none;
    }
    .inputButton:focus {
        outline: none;
    }

</style>

<div class="container">
    <div class="col-md-6">
        <div class="logbox">
            <form  method="post" action="sign_up">
                <h1>create an account</h1>
                <input name="login" type="text" placeholder="Login:" autofocus="autofocus" required="required" class="input pass"/>
                <input name="password" type="password" placeholder="Choose a password" required="required" class="input pass"/>
                <input name="retype_password" type="password" placeholder="Confirm password" required="required" class="input pass"/>
                <input type="submit" value="Sign me up!" class="inputButton"/>
            </form>
        </div>
    </div>
    <!--col-md-6-->

    <div class="col-md-6">
        <div class="logbox">
            <form  method="post" action="sign_in">
                <h1>account login</h1>
                <input name="login" type="text" placeholder="Enter your login" class="input pass"/>
                <input name="password" type="password" placeholder="Enter your password" required="required" class="input pass"/>
                <input type="submit" value="Sign me in!" class="inputButton"/>
                <div class="text-center">
        </div>
        </form>
    </div>
</div>
</div>