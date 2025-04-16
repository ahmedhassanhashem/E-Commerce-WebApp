<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="/home" />
</c:if>

<!DOCTYPE html>
<html class="no-js" lang="en">
<%@include file="commos/head.html" %>
<body class="config" id="js-scrollspy-trigger">
<%@include file="commos/preloader.html" %>

<div id="app">
    <jsp:include page="commos/header.jsp"/>

    <div class="app-content">
        <br><br>
        <div class="section__content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                        <div class="table-responsive">
                            <table class="table-p">
                                <tbody>
                                <c:if test="${not empty sessionScope.cart && not empty sessionScope.cart.items}">
                                    <c:forEach items="${sessionScope.cart.items}" var="item">
                                        <tr>
                                            <td>
                                                <div class="table-p__box">
                                                    <div class="table-p__img-wrap">
                                                        <img class="u-img-fluid" src="images/product/electronic/${item.product.image}.jpg" alt="">
                                                    </div>
                                                    <div class="table-p__info">
                                                        <span class="table-p__name">
                                                            <a href="product-details?id=${item.product.productId}&name=${item.product.name}">${item.product.name}</a>
                                                        </span>
                                                        <span class="table-p__category">
                                                            <a href="product-list?category=${item.product.category.name().toLowerCase()}">${item.product.category.name()}</a>
                                                        </span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <span class="table-p__price">$${item.product.price}</span>
                                            </td>
                                            <td>
                                                <div class="table-p__input-counter-wrap">
                                                    <div class="input-counter">
                                                        <span  class="input-counter__minus fas fa-minus"></span>
                                                        <input  class="input-counter__text input-counter--text-primary-style" type="text" value="${item.quantity}" data-min="1" data-max="${item.product.stock}"  data-item-id="${item.id}">
                                                        <span class="input-counter__plus fas fa-plus"></span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="table-p__del-wrap">
                                                    <a data-item-id="${item.id}" class="remove-item far fa-trash-alt table-p__delete-link" href="#"></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="container">
                        <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                            <form class="f-cart">
                                <div class="row">
                                    <div class="col-lg-4 col-md-6 u-s-m-b-30">
                                        <div class="f-cart__pad-box">
                                            <div class="u-s-m-b-30">
                                                <table class="f-cart__table">
                                                    <tbody>
                                                    <tr>
                                                        <td>TOTAL</td>
                                                        <td>$${sessionScope.cart.totalPrice}</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div>
                                                <button class="btn btn--e-brand-b-2" type="submit">PROCEED TO CHECKOUT</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <div class="route-box">
                            <div class="route-box__g1">
                                <a class="route-box__link" href="product-list"><i class="fas fa-long-arrow-alt-left"></i>
                                    <span>CONTINUE SHOPPING</span></a>
                            </div>
                            <div class="route-box__g2">
                                <a id="clear-cart-btn" class="route-box__link" href="#"><i class="fas fa-trash"></i>
                                    <span>CLEAR CART</span></a>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>
            </div>
        </div>
    </div>

    <%@include file="commos/footer.jsp" %>
</div>

<jsp:include page="commos/modals.jsp"/>
<%@include file="commos/script.html" %>
<script src="js/custom-js/cart.js"></script>
</body>
</html>