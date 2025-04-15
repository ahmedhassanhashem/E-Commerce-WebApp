<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="${pageContext.request.contextPath}/home" />
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

        <br><br>
        <!--====== Section Content ======-->
        <div class="section__content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">


                        <!--====== Wishlist Product ======-->
                        <div class="w-r u-s-m-b-30">
                            <div class="w-r__container">
                                <div class="w-r__wrap-1">
                                    <div class="w-r__img-wrap">

                                        <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></div>
                                    <div class="w-r__info">

                                                <span class="w-r__name">

                                                    <a href="product-details.jsp">New Fashion D Nice Elegant</a></span>

                                        <span class="w-r__category">

                                                    <a href="product-list?category=${product.category.name().toLowerCase()}">Men Clothing</a></span>

                                        <span class="w-r__price">$125.00

                                                    <span class="w-r__discount">$160.00</span></span></div>
                                </div>
                                <div class="w-r__wrap-2">

                                    <a class="w-r__link btn--e-brand-b-2" data-modal="modal" data-modal-id="#add-to-cart">ADD TO CART</a>

                                    <a class="w-r__link btn--e-transparent-platinum-b-2" href="product-details.jsp">VIEW</a>

                                    <a class="w-r__link btn--e-transparent-platinum-b-2" href="#">REMOVE</a></div>
                            </div>
                        </div>
                        <!--====== End - Wishlist Product ======-->
                    </div>
                    <div class="col-lg-12">
                        <div class="route-box">
                            <div class="route-box__g">

                                <a class="route-box__link" href="product-list"><i class="fas fa-long-arrow-alt-left"></i>

                                    <span>CONTINUE SHOPPING</span></a></div>
                            <div class="route-box__g">

                                <a class="route-box__link" href="wishlist.jsp"><i class="fas fa-trash"></i>

                                    <span>CLEAR WISHLIST</span></a></div>
                        </div>
                    </div>
                </div><br><br>
            </div>
        </div>
        <!--====== End - Section Content ======-->
    </div>
    <!--====== End - Section 2 ======-->
</div>


    <!--====== End -App Content ======-->


    <%@include file="commos/footer.jsp" %>


<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
