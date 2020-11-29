<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="view_user_order?id=${user.id}">Your order <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" id="searchBox"
                           onkeyup="myFunction()">
                </form>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="log_out">Log out</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row row-cols-2">
        <c:forEach items="${products}" var="product">
            <div class="col p-2">
                <div class="card">
                    <img src="${product.image}" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p>Product: ${product.name}</p>
                        <p>Description: ${product.description}</p>
                        <p>Price: ${product.price}</p>
                        <form method="POST" action="add_product_to_order">
                            <input type="hidden" name="add" value="${product.id}">
                            <input type="submit" class="btn btn-outline-success" value="Add to order" name="addButton"/>
                            <input type="hidden" name="userId" value="${user.id}">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    var $searchBox = $('#searchBox');
    var $userDivs = $('.col div');

    $searchBox.on('input', function () {
        var scope = this;
        if (!scope.value || scope.value == '') {
            $userDivs.show();
            return;
        }

        $userDivs.each(function (i, div) {
            var $div = $(div);
            $div.toggle($div.text().toLowerCase().indexOf(scope.value.toLowerCase()) > -1);
        })
    });
</script>

