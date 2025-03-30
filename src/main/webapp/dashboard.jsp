<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="commos/preloader.html"%>
<%@include file="commos/head.html"%>
<jsp:include page="commos/header.jsp"/>


<!DOCTYPE html>
<html class="no-js" lang="en">
    <body class="config">

        <!--====== Main App ======-->
        <div id="app">


            <!--====== App Content ======-->
            <div class="app-content">

                <!--====== Section 1 ======-->
                <div class="u-s-p-y-60">

                    <!--====== Section Content ======-->
                    <div class="section__content">
                        <div class="container">
                            <div class="breadcrumb">
                                <div class="breadcrumb__wrap">
                                    <ul class="breadcrumb__list">
                                        <li class="has-separator">

                                            <a href="index.jsp">Home</a></li>
                                        <li class="is-marked">

                                            <a href="dashboard.jsp">My Account</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section 1 ======-->


                <!--====== Section 2 ======-->
                <div class="u-s-p-b-60">

                    <!--====== Section Content ======-->
                    <div class="section__content">
                        <div class="dash">
                            <div class="container">
                                <div class="row">

                                    <!--====== Dashboard Features ======-->
                                    <div class="col-lg-3 col-md-12">
                                        <%@ include file="dashboard-sidebar.html" %>
                                        <jsp:include page="orders-statistics.jsp" />
                                    </div>
                                    <!--====== End - Dashboard Features ======-->

                                    <div class="col-lg-9 col-md-12">
                                        <jsp:include page="my-account-info.jsp" />
                                        <jsp:include page="recent-orders.jsp" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--====== End - Section Content ======-->
                </div>
                <!--====== End - Section 2 ======-->
            </div>
            <!--====== End - App Content ======-->


            <!--====== Main Footer ======-->
            <%@include file="commos/footer.html"%>
        </div>
        <!--====== End - Main App ======-->

        <jsp:include page="commos/modals.jsp"/>
        <%@include file="commos/script.html"%>
    </body>
</html>