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
                                        <h1 class="dash__h1 u-s-m-b-14">My Balance</h1>
                                        <span class="dash__text u-s-m-b-30">here you can show your balance.</span>



                                        <form class="dash-edit-p" action="update-profile" method="POST" id="profileForm">


                                            <div class="row">


                                                <div class="col-lg-4 u-s-m-b-30">
                                                    <label class="gl-label" for="reg-credit">Add Credit Balance</label>
                                                    <input class="input-text input-text--primary-style" type="text"
                                                           id="reg-credit" name="credit" value="Add Credit">
                                                    <div class="error-message" id="name-error"></div>
                                                </div>

                                                <div class="col-lg-4 u-s-m-b-30">
                                                    <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                                                        <div class="dash__pad-3">
                                                            <h2 class="dash__h2 u-s-m-b-8">Current Credit Balance</h2>
                                                            <span class="dash__text">920</span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-lg-6 u-s-m-b-30">
                                                    <button class="btn btn--e-brand-b-2" type="submit">ADD</button>
                                                </div>

                                            </div>
                                        </form>

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
