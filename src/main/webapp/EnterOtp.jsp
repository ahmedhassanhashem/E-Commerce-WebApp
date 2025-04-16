<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <link href="css/EnterOTP.css" rel="stylesheet">
</head>
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
                                    <h1 class="gl-h1">ENTER VERIFICATION CODE</h1>

                                    <span class="gl-text u-s-m-b-30">Please enter the 6-digit verification code sent to your email.</span>
                                    
                                    <% if(request.getAttribute("status") != null && request.getAttribute("status").equals("failed")) { %>
                                    <div class="alert alert-danger" style="color: red; margin-bottom: 20px;">
                                        Invalid verification code. Please try again.
                                    </div>
                                    <% } %>
                                    
                                    <% if(session.getAttribute("message") != null) { %>
                                    <div class="alert alert-success" style="color: green; margin-bottom: 20px;">
                                        <%= session.getAttribute("message") %>
                                    </div>
                                    <% } %>
                                    
                                    <form class="l-f-o__form" action="validateOTP" method="post" id="otpForm">
                                        <div class="u-s-m-b-30">
                                            <label class="gl-label" for="otp">VERIFICATION CODE *</label>
                                            <div class="otp-container">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 0)">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 1)">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 2)">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 3)">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 4)">
                                                <input class="otp-input" type="number" maxlength="1" required oninput="moveToNext(this, 5)">
                                            </div>
                                            <input type="hidden" name="otp" id="otp" value="">
                                            <input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
                                        </div>
                                        <div class="u-s-m-b-30">
                                            <button class="btn btn--e-transparent-brand-b-2" type="submit">VERIFY</button>
                                        </div>
                                        
                                        <div class="u-s-m-b-30">
                                            <a class="gl-link" href="forgot-password.jsp">Back to Password Reset</a>
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
<script src="js/EnterOTP.js"></script>
</body>
</html>