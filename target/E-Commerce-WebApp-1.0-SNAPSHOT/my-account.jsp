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


                                <%@include file="commos/dashboard-sidebar-links.html" %>
                                <jsp:include page="commos/dashboard-sidebar-orders-statistics.jsp"/>


                            </div>


                            <div class="col-lg-9 col-md-12">


                                <jsp:include page="my-account/info.jsp"/>
                                <jsp:include page="my-account/orders-summary.jsp"/>


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
