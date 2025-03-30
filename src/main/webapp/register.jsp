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
        <!--====== Section Content ======-->
        <div class="section__content">
            <div class="container">
                <div class="row row--center">
                    <div class="col-lg-6 col-md-8 u-s-m-b-30">
                        <div class="l-f-o">
                            <div class="l-f-o__pad-box">
                                <h1 class="gl-h1">PERSONAL INFORMATION</h1>
                                <form class="l-f-o__form">

                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="reg-name">NAME *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="reg-name" placeholder="Name">
                                    </div>

                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="reg-address">ADDRESS *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="reg-address" placeholder="Name">
                                    </div>


                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="reg-email">E-MAIL *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="reg-email" placeholder="Enter E-mail"></div>
                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="reg-password">PASSWORD *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="reg-password" placeholder="Enter Password"></div>
                                    <div class="u-s-m-b-15">

                                        <button class="btn btn--e-transparent-brand-b-2" type="submit">CREATE</button></div>

                                    <a class="gl-link" href="#">Return to Store</a>
                                </form>
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
