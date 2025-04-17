<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="/home" />
</c:if>


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


                                <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white">
                                    <div class="dash__pad-2">
                                        <h1 class="dash__h1 u-s-m-b-14">Edit Profile</h1><br>

                                        <div class="row">
                                            <div class="col-lg-7">
                                                <form class="dash-edit-p" action="update-profile" method="POST"
                                                      id="profileForm">


                                                    <div class="u-s-m-b-30">
                                                        <label class="gl-label" for="reg-name">NAME</label>
                                                        <input class="input-text input-text--primary-style"
                                                               type="text"
                                                               id="reg-name"
                                                               name="name"
                                                               value="${user.name}">
                                                        <div class="error-message" id="name-error"></div>
                                                    </div>


                                                    <div class="u-s-m-b-30">
                                                        <label class="gl-label" for="reg-email">EMAIL</label>
                                                        <input class="input-text input-text--primary-style"
                                                               type="email"
                                                               id="reg-email"
                                                               name="email"
                                                               value="${user.email}">
                                                        <div class="error-message" id="email-error"></div>
                                                    </div>


                                                    <div class="u-s-m-b-30">
                                                        <label class="gl-label" for="reg-phone">PHONE</label>
                                                        <input class="input-text input-text--primary-style"
                                                               type="text"
                                                               id="reg-phone"
                                                               name="phone"
                                                               value="${user.phone}"
                                                               maxlength="11">
                                                        <div class="error-message" id="phone-error"></div>
                                                    </div>


                                                    <div class="u-s-m-b-30">
                                                        <label class="gl-label" for="reg-phone">ADDRESS</label>
                                                        <input class="input-text input-text--primary-style"
                                                               type="text"
                                                               id="reg-address"
                                                               name="address"
                                                               value="${user.address}">
                                                        <span class="error-message" id="address-error"></span>
                                                    </div>


                                                    <button class="btn btn--e-brand-b-2" type="submit">SAVE</button>
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
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

<!--====== edit-profile-validations ======-->
<script src="js/custom-js/edit-profile-validations.js"></script>

</body>
</html>
