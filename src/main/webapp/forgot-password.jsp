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
        <!--====== Section 2 ======-->
        <div class="u-s-p-b-60">
            <br>
            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row row--center">
                        <div class="col-lg-6 col-md-8 u-s-m-b-30">
                            <div class="l-f-o">
                                <div class="l-f-o__pad-box">
                                    <h1 class="gl-h1">PASSWORD RESET</h1>

                                    <span class="gl-text u-s-m-b-30">Enter your email and credit card number you registered with to reset your password.</span>
                                    
                                    <% if(request.getAttribute("errorMessage") != null) { %>
                                    <div class="alert alert-danger" style="color: red; margin-bottom: 20px;">
                                        <%= request.getAttribute("errorMessage") %>
                                    </div>
                                    <% } %>
                                    
                                    <form class="l-f-o__form" action="forgotPassword" method="post">
                                        <div class="u-s-m-b-30">
                                            <label class="gl-label" for="reset-email">E-MAIL *</label>
                                            <input class="input-text input-text--primary-style" type="text" id="reset-email" name="reset-email" placeholder="Enter E-mail">
                                        </div>
                                        
                                        <div class="u-s-m-b-30">
                                            <label class="gl-label" for="reset-cc">CREDIT CARD NUMBER *</label>
                                            <input class="input-text input-text--primary-style" type="text" id="reset-cc" name="reset-cc" placeholder="Enter Credit Card Number">
                                        </div>
                                        
                                        <div class="u-s-m-b-30">
                                            <button class="btn btn--e-transparent-brand-b-2" type="submit">SUBMIT</button>
                                        </div>
                                        
                                        <div class="u-s-m-b-30">
                                            <a class="gl-link" href="login.jsp">Back to Login</a>
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
        <!--====== End - Section 2 ======-->

    </div>
    <!--====== End -App Content ======-->

    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======-->

<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>