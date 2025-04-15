<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>


<!DOCTYPE html>
<html class="no-js" lang="en">


<%@include file="commos/head.html"%>



<body class="config" id="js-scrollspy-trigger">

<%@include file="commos/preloader.html"%>

<!--====== Main App ======-->
<div id="app">


    <jsp:include page="commos/header.jsp"/>


    <!--====== App Content ======-->
    <div class="app-content">

        <br>
        <!--====== Section 3 ======-->
        <div class="u-s-p-b-60">
            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                            <div class="contact-o u-h-100">
                                <div class="contact-o__wrap">
                                    <div class="contact-o__icon"><i class="fas fa-phone-volume"></i></div>

                                    <span class="contact-o__info-text-1">LET'S HAVE A CALL</span>

                                    <span class="contact-o__info-text-2">(+02) 123 456 7890</span>

                                    <span class="contact-o__info-text-2">(+02) 123 456 7890</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                            <div class="contact-o u-h-100">
                                <div class="contact-o__wrap">
                                    <div class="contact-o__icon"><i class="fas fa-map-marker-alt"></i></div>

                                    <span class="contact-o__info-text-1">OUR LOCATION</span>

                                    <span class="contact-o__info-text-2">ITI, Smart Village</span>

                                    <span class="contact-o__info-text-2">Giza, Egypt</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                            <div class="contact-o u-h-100">
                                <div class="contact-o__wrap">
                                    <div class="contact-o__icon"><i class="far fa-clock"></i></div>

                                    <span class="contact-o__info-text-1">WORK TIME</span>

                                    <span class="contact-o__info-text-2">5 Days a Week</span>

                                    <span class="contact-o__info-text-2">From 9 AM to 7 PM</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 3 ======-->


        <!--====== Section 4 ======-->
        <div class="u-s-p-b-60">

            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="contact-area u-h-100">
                                <div class="contact-area__heading">
                                    <h2>Get In Touch</h2>
                                </div>
                                <form class="contact-f" method="post" action="index.html">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 u-h-100">
                                            <div class="u-s-m-b-30">

                                                <label for="c-name"></label>

                                                <input class="input-text input-text--border-radius input-text--primary-style" type="text" id="c-name" placeholder="Name (Required)" required></div>
                                            <div class="u-s-m-b-30">

                                                <label for="c-email"></label>

                                                <input class="input-text input-text--border-radius input-text--primary-style" type="text" id="c-email" placeholder="Email (Required)" required></div>
                                            <div class="u-s-m-b-30">

                                                <label for="c-subject"></label>

                                                <input class="input-text input-text--border-radius input-text--primary-style" type="text" id="c-subject" placeholder="Subject (Required)" required></div>
                                        </div>
                                        <div class="col-lg-6 col-md-6 u-h-100">
                                            <div class="u-s-m-b-30">

                                                <label for="c-message"></label><textarea class="text-area text-area--border-radius text-area--primary-style" id="c-message" placeholder="Compose a Message (Required)" required></textarea></div>
                                        </div>
                                        <div class="col-lg-12">

                                            <button class="btn btn--e-brand-b-2" type="submit">Send Message</button></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 4 ======-->




    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.jsp"%>

</div>
<!--====== End - Main App  ======-->





<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html"%>

</body>
</html>
