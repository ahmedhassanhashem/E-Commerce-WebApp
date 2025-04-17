<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="$/home" />
</c:if>


<!DOCTYPE html>
<html class="no-js" lang="en">


<%@include file="commos/head.html" %>


<body class="config" id="js-scrollspy-trigger">

<%@include file="commos/preloader.html" %>

<!--====== Main App ======-->
<div id="app">


    <jsp:include page="commos/header.jsp"/>


    <!--====== App Content ======-->
    <div class="app-content">


        <!-- page content -->
        <!--====== Section 3 ======-->
            <br><br>
            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="checkout-f">
                        <div class="row">

                            <div class="col-lg-12">


                                <!--====== Order Summary ======-->
                                <div class="o-summary">
                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__item-wrap gl-scroll">
<c:forEach items="${sessionScope.cart.items}" var="item">

                                            <div class="o-card">
                                                <div class="o-card__flex">
                                                    <div class="o-card__img-wrap">

                                                        <img class="u-img-fluid" src="images/product/electronic/${item.image}.jpg" alt=""></div>
                                                    <div class="o-card__info-wrap">

                                                            <span class="o-card__name">

                                                                <a href="product-details?id=name=${item.productId}&name=${item.name}">${item.product.name}</a></span>

                                                        <span class="o-card__quantity">Quantity x ${item.quantity}</span>

                                                        <span class="o-card__price">${item.product.price}</span></div>
                                                </div>

                                                <a class="remove-item o-card__del far fa-trash-alt"></a>
                                            </div>

</c:forEach>
                                        </div>

                                    </div>
                                </div>


                                <div class="col-lg-6">

                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__box">
                                            <div class="ship-b">

                                                <span class="ship-b__text">Ship to:</span>
                                                <div class="ship-b__box u-s-m-b-10">
                                                    <p class="ship-b__p">${sessionScope.user.address}</p>
                                                    <p class="ship-b__p">${sessionScope.user.phone}</p>

                                                    <a class="ship-b__edit btn--e-transparent-platinum-b-2" data-modal="modal" data-modal-id="#edit-ship-address" href="my-profile.jsp">Edit</a>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="o-summary__section u-s-m-b-30">
                                            <form class="checkout-f" action="checkout">
                                                <div class="o-summary__box">
                                                    <table class="o-summary__table">
                                                        <tbody>
                                                        <tr>
                                                            <td>TOTAL</td>
                                                            <td>${sessionScope.user.cart.TotalPrice}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <br>
                                                    <div>

                                                        <button class="btn btn--e-brand-b-2" type="submit">PLACE ORDER</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!--====== End - Order Summary ======-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section Content ======-->
            </div>

    <!--====== End -App Content ======-->


    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
