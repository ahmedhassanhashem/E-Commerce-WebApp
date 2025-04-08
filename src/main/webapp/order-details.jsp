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



                                <div class="col-lg-9 col-md-12">
                                    <h1 class="dash__h1 u-s-m-b-30">Order Details</h1>

                                    <!-- Order Details: Order # and Total -->
                                    <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                        <div class="dash__pad-2">
                                            <div class="dash-l-r">
                                                <div>
                                                    <div class="manage-o__text-2 u-c-secondary">Order #305423126</div>
                                                    <!-- Date removed -->
                                                </div>
                                                <div>
                                                    <div class="manage-o__text-2 u-c-silver">
                                                        Total: <span class="manage-o__text-2 u-c-secondary">$16.00</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Shipping Address Section -->
                                    <div class="dash__box dash__box--bg-white dash__box--shadow u-s-m-b-30">
                                        <div class="dash__pad-3">
                                            <h2 class="dash__h2 u-s-m-b-8">Shipping Address</h2><br>
                                            <h2 class="dash__h2 u-s-m-b-8">John Doe</h2>
                                            <span class="dash__text-2">4247 Ashford Drive Virginia - VA-20006 - USA</span>
                                            <span class="dash__text-2">(+0) 900901904</span>
                                        </div>
                                    </div>

                                    <!-- Items Section (Scrollable Table) -->
                                    <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                        <div class="dash__pad-2">
                                            <div class="manage-o__header u-s-m-b-20">
                                                <div class="manage-o__icon">
                                                    <i class="fas fa-box u-s-m-r-5"></i>
                                                    <span class="manage-o__text">Items Ordered</span>
                                                </div>
                                            </div>

                                            <div class="dash__table-wrap gl-scroll">
                                                <table class="dash__table">
                                                    <thead>
                                                    <tr>
                                                        <th>Item</th>
                                                        <th>Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td>Yellow Wireless Headphone</td>
                                                        <td>1</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Bluetooth Speaker</td>
                                                        <td>2</td>
                                                    </tr>
                                                    <tr>
                                                        <td>USB Type-C Charger</td>
                                                        <td>3</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
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


    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
