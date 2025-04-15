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
                                    <h1 class="gl-h1">SET NEW PASSWORD</h1>

                                    <span class="gl-text u-s-m-b-30">Enter your new password below.</span>
                                    
                                    <% if(request.getAttribute("errorMessage") != null) { %>
                                    <div class="alert alert-danger" style="color: red; margin-bottom: 20px;">
                                        <%= request.getAttribute("errorMessage") %>
                                    </div>
                                    <% } %>
                                    
                                    <% 
                                    // Check if session has the required reset attributes
                                    if(session.getAttribute("resetEmail") == null || session.getAttribute("resetToken") == null) {
                                    %>
                                        <div class="alert alert-danger" style="color: red; margin-bottom: 20px;">
                                            Invalid or expired password reset session. Please try again.
                                        </div>
                                        <div class="u-s-m-b-30">
                                            <a class="btn btn--e-transparent-brand-b-2" href="forgotPassword">Return to Password Reset</a>
                                        </div>
                                    <% } else { %>
                                        <form class="l-f-o__form" action="forgotPassword" method="post" onsubmit="return validateResetForm()">
                                            <input type="hidden" name="action" value="reset">
                                            
                                            <div class="u-s-m-b-30">
                                                <label class="gl-label" for="new-password">NEW PASSWORD *</label>
                                                <input class="input-text input-text--primary-style" type="password" id="new-password" name="new-password" placeholder="Enter New Password">
                                                <div id="password-strength" style="height: 5px; margin-top: 5px; width: 0%;"></div>
                                                <div id="password-requirements" style="font-size: 12px; margin-top: 5px; color: #666;">
                                                    Password must contain at least 8 characters with uppercase, lowercase, and numbers
                                                </div>
                                            </div>
                                            
                                            <div class="u-s-m-b-30">
                                                <label class="gl-label" for="confirm-password">CONFIRM PASSWORD *</label>
                                                <input class="input-text input-text--primary-style" type="password" id="confirm-password" name="confirm-password" placeholder="Confirm New Password">
                                                <div id="password-error" style="color: red; display: none;">Passwords do not match</div>
                                            </div>
                                            
                                            <div class="u-s-m-b-30">
                                                <button class="btn btn--e-transparent-brand-b-2" type="submit">RESET PASSWORD</button>
                                            </div>
                                            
                                            <div class="u-s-m-b-30">
                                                <a class="gl-link" href="login.jsp">Back to Login</a>
                                            </div>
                                        </form>
                                    <% } %>
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
<script src="js/forgotpassword.js"></script>

</body>
</html>