<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


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


        <!--====== Section 2 ======-->
        <div class="u-s-p-b-60">

            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="faq">
                                <h3 class="faq__heading">FREQUENTLY QUESTIONS</h3>
                                <h3 class="faq__heading">Below are frequently asked questions, you may find the answer for yourself.</h3>
                                <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 2 ======-->


        <!--====== Section 3 ======-->
        <div class="u-s-p-b-60">

            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="faq-accordion-group">
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-1" data-toggle="collapse">How can I get discount coupon ?</a>
                                    <div class="faq__answer collapse" id="faq-1" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-2" data-toggle="collapse">Do I need to create account for buy products ?</a>
                                    <div class="faq__answer collapse" id="faq-2" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-3" data-toggle="collapse">How can I track my order ?</a>
                                    <div class="faq__answer collapse" id="faq-3" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-4" data-toggle="collapse">What is the payment security system ?</a>
                                    <div class="faq__answer collapse" id="faq-4" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-5" data-toggle="collapse">What policy do you have for product sell ?</a>
                                    <div class="faq__answer collapse" id="faq-5" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-6" data-toggle="collapse">How I Return back my product ?</a>
                                    <div class="faq__answer collapse" id="faq-6" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-7" data-toggle="collapse">What Payment Methods are Available ?</a>
                                    <div class="faq__answer collapse" id="faq-7" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                                <div class="faq__list">

                                    <a class="faq__question collapsed" href="#faq-8" data-toggle="collapse">What Shipping Methods are Available ?</a>
                                    <div class="faq__answer collapse" id="faq-8" data-parent="#faq-accordion-group">
                                        <p class="faq__text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 3 ======-->




    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.html"%>

</div>
<!--====== End - Main App  ======-->





<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html"%>

</body>
</html>
