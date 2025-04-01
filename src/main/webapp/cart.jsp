<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


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
        <!--====== Section Content ======-->
        <br><br>
        <div class="section__content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                        <div class="table-responsive">
                            <table class="table-p">
                                <tbody>

                                <!--====== Row ======-->
                                <tr>
                                    <td>
                                        <div class="table-p__box">
                                            <div class="table-p__img-wrap">

                                                <img class="u-img-fluid" src="images/product/men/product8.jpg" alt=""></div>
                                            <div class="table-p__info">

                                                            <span class="table-p__name">

                                                                <a href="product-details.jsp">New Fashion D Nice Elegant</a></span>

                                                <span class="table-p__category">

                                                                <a href="product-list.jsp">Men Clothing</a></span>
                                                <ul class="table-p__variant-list">
                                                    <li>

                                                        <span>Size: 22</span></li>
                                                    <li>

                                                        <span>Color: Red</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                    <td>

                                        <span class="table-p__price">$125.00</span></td>
                                    <td>
                                        <div class="table-p__input-counter-wrap">

                                            <!--====== Input Counter ======-->
                                            <div class="input-counter">

                                                <span class="input-counter__minus fas fa-minus"></span>

                                                <input class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="1000">

                                                <span class="input-counter__plus fas fa-plus"></span></div>
                                            <!--====== End - Input Counter ======-->
                                        </div>
                                    </td>
                                    <td>
                                        <div class="table-p__del-wrap">

                                            <a class="far fa-trash-alt table-p__delete-link" href="#"></a></div>
                                    </td>
                                </tr>
                                <!--====== End - Row ======-->
                                </tbody>
                            </table>
                        </div>
                    </div>


                    <!-- total and checkout -->
                    <!--====== Section Content ======-->
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
                                                            <td>$379.00</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div>

                                                    <button class="btn btn--e-brand-b-2" type="submit"> PROCEED TO CHECKOUT</button></div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                        </div>
                    </div>





                    <div class="col-lg-12">
                        <div class="route-box">
                            <div class="route-box__g1">

                                <a class="route-box__link" href="product-list.jsp"><i class="fas fa-long-arrow-alt-left"></i>

                                    <span>CONTINUE SHOPPING</span></a></div>
                            <div class="route-box__g2">

                                <a class="route-box__link" href="#"><i class="fas fa-trash"></i>

                                    <span>CLEAR CART</span></a>

                            </div>
                        </div>
                    </div>
                </div>
                <br><br>
            </div>
        </div>
        <!--====== End - Section Content ======-->
    </div>
    <!--====== End - Section 2 ======-->


    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.html" %>

<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
