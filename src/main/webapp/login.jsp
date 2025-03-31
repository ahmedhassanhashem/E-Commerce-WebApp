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
        <br>
        <div class="section__content">
            <div class="container">
                <div class="row row--center">
                    <div class="col-lg-6 col-md-8 u-s-m-b-30">
                        <div class="l-f-o">
                            <div class="l-f-o__pad-box">

                                <h1 class="gl-h1">Login</h1>

                                <span class="gl-text u-s-m-b-30">If you have an account with us, please log in.</span>
                                <form class="l-f-o__form">

                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="login-email">E-MAIL *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="login-email" placeholder="Enter E-mail"></div>
                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="login-password">PASSWORD *</label>

                                        <input class="input-text input-text--primary-style" type="text" id="login-password" placeholder="Enter Password"></div>
                                    <div class="gl-inline">
                                        <div class="u-s-m-b-30">

                                            <button class="btn btn--e-transparent-brand-b-2" type="submit">LOGIN</button></div>
                                        <div class="u-s-m-b-30">

                                            <a class="gl-link" href="lost-password.html">Lost Your Password?</a></div>
                                    </div>
                                    <div class="u-s-m-b-30">

                                        <!--====== Check Box ======-->
                                        <div class="check-box">

                                            <input type="checkbox" id="remember-me">
                                            <div class="check-box__state check-box__state--primary">

                                                <label class="check-box__label" for="remember-me">Remember Me</label></div>
                                        </div>
                                        <!--====== End - Check Box ======-->
                                    </div>
                                        <h1 class="gl-h1">NEW CUSTOMER?</h1>

                                        <span class="gl-text u-s-m-b-30">By creating an account with our store, you will be able to move through the checkout process faster, store shipping addresses, view and track your orders in your account and more.</span>
                                        <div class="u-s-m-b-15">

                                            <a class="l-f-o__create-link btn--e-transparent-brand-b-2" href="signup.html">CREATE AN ACCOUNT</a>
                                        </div>
                                </form>
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


<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
