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
        <br>
        <div class="u-s-p-b-60">
            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="dash">
                    <div class="container">
                        <div class="row">





                            <div class="col-lg-3 col-md-12">


                                <%@include file="commos/dashboard-sidebar-links.html"%>
                                <jsp:include page="commos/dashboard-sidebar-orders-statistics.jsp"/>


                            </div>





                            <div class="col-lg-9 col-md-12">




                                <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                    <div class="dash__pad-2">
                                        <h1 class="dash__h1 u-s-m-b-14">My Orders</h1>
                                        <span class="dash__text u-s-m-b-30">Here you can see all products that have been delivered.</span>

                                        <div class="m-order__list">
                                            <div class="m-order__get">
                                                <div class="manage-o__header u-s-m-b-30">
                                                    <div class="dash-l-r">
                                                        <div>
                                                            <div class="manage-o__text-2 u-c-secondary">Order #305423126</div>
                                                            <!-- Date section removed -->
                                                        </div>
                                                        <div>
                                                            <div class="dash__link dash__link--brand">
                                                                <a href="order-details.jsp">MANAGE</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="manage-o__description">
                                                    <div class="description__container">
                                                        <!-- Image removed -->
                                                        <div class="description-title">Items Ordered</div>
                                                        <div class="description__items">
                                                            <ul class="order-item-list" style="list-style: decimal;">
                                                                <li>
                                                                    <span class="order-item-name">Yellow Wireless Headphone</span>
                                                                    <span class="order-item-qty-label"> &nbsp;&nbsp; Quantity: </span>
                                                                    <span class="order-item-quantity">1</span>
                                                                </li>
                                                                <li>
                                                                    <span class="order-item-name">Yellow Wireless Headphone</span>
                                                                    <span class="order-item-qty-label"> &nbsp;&nbsp; Quantity: </span>
                                                                    <span class="order-item-quantity">21</span>
                                                                </li>
                                                                <li>
                                                                    <span class="order-item-name">Yellow Wireless Headphone</span>
                                                                    <span class="order-item-qty-label"> &nbsp;&nbsp; Quantity: </span>
                                                                    <span class="order-item-quantity">11</span>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="description__info-wrap">
                                                        <div>
                                                            <span class="manage-o__badge badge--processing">Processing</span>
                                                        </div>
                                                        <!-- Removed the separate quantity section -->
                                                        <div>
                                                    <span class="manage-o__text-2 u-c-silver">Total:
                                                      <span class="manage-o__text-2 u-c-secondary">$16.00</span>
                                                    </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>


                            </div>



                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>









    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.html" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
