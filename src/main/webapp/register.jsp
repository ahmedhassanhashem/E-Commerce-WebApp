<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>

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
                                
                                <!-- Display error message if any -->
                                <% if(request.getAttribute("errorMessage") != null) { %>
                                <div class="alert alert-danger" role="alert">
                                    <%= request.getAttribute("errorMessage") %>
                                </div>
                                <% } %>
                                
                                <form class="l-f-o__form" action="register" method="post">
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-name">NAME *</label>
                                        <input class="input-text input-text--primary-style" type="text" id="reg-name" name="name" placeholder="Name" required>
                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-email">E-MAIL *</label>
                                        <input class="input-text input-text--primary-style" type="email" id="reg-email" name="email" placeholder="E-mail" required>
                                        <div id="email-validation-message" class="u-s-m-t-10" style="min-height: 20px;"></div>

                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-phone">PHONE *</label>
                                        <input class="input-text input-text--primary-style" type="text" id="reg-phone" name="phone" placeholder="Phone" required>
                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-address">ADDRESS *</label>
                                        <input class="input-text input-text--primary-style" type="text" id="reg-address" name="address" placeholder="Address" required>
                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-credit">CREDIT *</label>
                                        <input class="input-text input-text--primary-style" type="number" id="reg-credit" name="credit" placeholder="Credit Balance" required>
                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-password">PASSWORD *</label>
                                        <input class="input-text input-text--primary-style" type="password" id="reg-password" name="password" placeholder="Password" required>
                                    </div>
                                
                                    <div class="u-s-m-b-30">
                                        <label class="gl-label" for="reg-confirm-password">CONFIRM PASSWORD *</label>
                                        <input class="input-text input-text--primary-style" type="password" id="reg-confirm-password" name="confirmPassword" placeholder="Confirm Password" required>
                                    </div>
                                
                                    <div class="u-s-m-b-15">
                                        <button class="btn btn--e-brand-b-2" type="submit">CREATE</button>
                                    </div>
                                
                                    <a class="gl-link" href="login.jsp">LOGIN</a>
                                </form>
                                
                            </div>
                        </div>
                     </div>
                </div>
            </div>
        </div>
    </div>
    <!--====== End -App Content ======-->

    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======-->

<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

<script src="js/register.js"></script>

</body>
</html>