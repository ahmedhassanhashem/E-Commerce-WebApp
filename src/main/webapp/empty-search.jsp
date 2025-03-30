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

        <!--====== Section 1 ======-->
        <div class="u-s-p-y-60">

            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 u-s-m-b-30">
                            <div class="empty">
                                <div class="empty__wrap">

                                    <span class="empty__big-text">SORRY</span>

                                    <span class="empty__text-1">Your search, did not match any products. A partial match of your keywords is listed below.</span>

                                    <span class="empty__text-2">Related searches:

                                            <a href="shop-side-version-2.html">Coffee Beans</a>

                                            <a href="shop-side-version-2.html">Coffee Mugs</a>

                                            <a href="shop-side-version-2.html">Coffee Machines</a></span>
                                    <form class="empty__search-form">

                                        <label for="search-label"></label>

                                        <input class="input-text input-text--primary-style" type="text" id="search-label" placeholder="Search Keywords">

                                        <button class="btn btn--icon fas fa-search" type="submit"></button></form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 1 ======-->


    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.html" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
