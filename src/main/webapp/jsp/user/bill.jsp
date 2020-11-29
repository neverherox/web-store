<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="user">Shop <span class="sr-only">(current)</span></a>
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
    <div class="card">
        <div class="card-header">
            Invoice
            <strong><%= (new java.util.Date()).toLocaleString()%></strong>
            <span class="float-right"> <strong>Status:</strong> PAID</span>
        </div>
        <div class="card-body">
            <div class="row mb-4">
                <div class="col-sm-6">
                    <h6 class="mb-3">To:</h6>
                    <div>
                        <strong>${firstName} &nbsp; ${lastName}</strong>
                    </div>
                    <div>${country}</div>
                    <div>${city}</div>
                    <div>${address}</div>
                </div>
            </div>
            <table class="table table-hover" id = "myTable">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Description</th>
                    <th class="text-center">Price</th>
                    <th> </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td class="col-sm-8 col-md-6">
                            <div class="media">
                                <a class="thumbnail pull-left" href="#"> <img class="media-object"
                                                                              src="${product.image}"
                                                                              style="width: 72px; height: 72px;"> </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">${product.name}</a></h4>
                                </div>
                            </div>
                        </td>
                        <td class="col-sm-1 col-md-1" style="text-align: center">
                            <strong>${product.description}</strong>
                        </td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>$${product.price}</strong></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>  </td>
                    <td>  </td>
                    <td><h3>Total</h3></td>
                    <td class="text-right"><h3><strong>$${order_price}</strong></h3></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>