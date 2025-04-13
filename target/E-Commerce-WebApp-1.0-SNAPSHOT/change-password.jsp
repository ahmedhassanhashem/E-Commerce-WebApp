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
                                <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white">
                                    <div class="dash__pad-2">
                                        <h1 class="dash__h1 u-s-m-b-14">Change Password</h1><br>

                                        <div class="row">
                                            <div class="col-lg-8">
                                                <form class="dash-edit-p" method="POST" id="passwordForm">
                                                    <!-- Current Password -->
                                                    <div class="gl-inline">
                                                        <div class="u-s-m-b-30">
                                                            <label class="gl-label" for="current-password">CURRENT PASSWORD</label>
                                                            <input class="input-text input-text--primary-style"
                                                                   type="password"
                                                                   id="current-password"
                                                                   name="currentPassword"
                                                                   required>
                                                            <span id="current-password-status" class="error-message"></span>
                                                        </div>
                                                    </div>

                                                    <!-- New Password -->
                                                    <div class="gl-inline">
                                                        <div class="u-s-m-b-30">
                                                            <label class="gl-label" for="new-password">NEW PASSWORD</label>
                                                            <input class="input-text input-text--primary-style"
                                                                   type="password"
                                                                   id="new-password"
                                                                   name="newPassword"
                                                                   required>
                                                            <span id="new-password-status" class="error-message"></span>
                                                        </div>
                                                    </div>

                                                    <!-- Confirm Password -->
                                                    <div class="gl-inline">
                                                        <div class="u-s-m-b-30">
                                                            <label class="gl-label" for="confirm-new-password">CONFIRM NEW PASSWORD</label>
                                                            <input class="input-text input-text--primary-style"
                                                                   type="password"
                                                                   id="confirm-new-password"
                                                                   name="confirmPassword"
                                                                   required>
                                                            <span id="confirm-password-status" class="error-message"></span>
                                                        </div>
                                                    </div>

                                                    <button class="btn btn--e-brand-b-2" type="submit">SUBMIT</button>
                                                    <div id="form-status"></div>
                                                </form>
                                            </div>
                                        </div>
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


    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======--


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
