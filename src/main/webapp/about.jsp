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
                            <div class="about">
                                <div class="about__container">
                                    <div class="about__info">
                                        <h2 class="about__h2">Welcome to hot coffee Store!</h2>
                                        <div class="about__p-wrap">
                                            <p class="about__p">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                                        </div>

                                        <a class="about__link btn--e-secondary" href="index.jsp" target="_blank">Shop Now</a>
                                    </div>
                                </div>
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

            <!--====== Section Intro ======-->
            <div class="section__intro u-s-m-b-46">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="section__text-wrap">
                                <h1 class="section__heading u-c-secondary">Our Team Members</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Intro ======-->


            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">
                    <div class="row">

                        <div class="col-lg-3 col-md-4 col-sm-6 u-s-m-b-30">
                            <div class="team-member u-h-100">
                                <div class="team-member__wrap">
                                    <div class="aspect aspect--bg-grey-fb aspect--square">

                                        <img class="aspect__img team-member__img" src="images/about/member-4.jpg" alt=""></div>
                                    <div class="team-member__social-wrap">
                                        <ul class="team-member__social-list">
                                            <li>

                                                <a class="s-tw--bgcolor-hover" href="#"><i class="fab fa-twitter"></i></a></li>
                                            <li>

                                                <a class="s-fb--bgcolor-hover" href="#"><i class="fab fa-facebook-f"></i></a></li>
                                            <li>

                                                <a class="s-insta--bgcolor-hover" href="#"><i class="fab fa-instagram"></i></a></li>
                                            <li>

                                                <a class="s-linked--bgcolor-hover" href="#"><i class="fab fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="team-member__info">

                                    <span class="team-member__name">ITIan</span>

                                    <span class="team-member__job-title">Student</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>
        <!--====== End - Section 3 ======-->


        <!--====== Section 4 ======-->
        <div class="u-s-p-b-90 u-s-m-b-30">

            <!--====== Section Intro ======-->
            <div class="section__intro u-s-m-b-46">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="section__text-wrap">
                                <h1 class="section__heading u-c-secondary u-s-m-b-12">CLIENTS FEEDBACK</h1>

                                <span class="section__span u-c-silver">WHAT OUR CLIENTS SAY</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section Intro ======-->


            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="container">

                    <!--====== Testimonial Slider ======-->
                    <div class="slider-fouc">
                        <div class="owl-carousel" id="testimonial-slider">



                            <div class="testimonial">
                                <div class="testimonial__img-wrap">

                                    <img class="testimonial__img" src="images/about/test-4.jpg" alt=""></div>
                                <div class="testimonial__content-wrap">

                                    <span class="testimonial__double-quote"><i class="fas fa-quote-right"></i></span>
                                    <blockquote class="testimonial__block-quote">
                                        <p>"Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean."</p>
                                    </blockquote>

                                    <span class="testimonial__author">John D. / DVNTR Inc.</span>
                                </div>
                            </div>



                        </div>
                    </div>
                    <!--====== End - Testimonial Slider ======-->
                </div>
            </div>
            <!--====== End - Section Content ======-->
        </div>


    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.jsp"%>

</div>
<!--====== End - Main App  ======-->





<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html"%>

</body>
</html>
