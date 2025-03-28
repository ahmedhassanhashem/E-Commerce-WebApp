<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="commos/preloader.html"%>
<%@include file="commos/head.html"%>
<jsp:include page="commos/header.jsp"/>


<!DOCTYPE html>
<html class="no-js" lang="en">

    <body class="config">


        <!--====== Main App ======-->
        <div id="app">

            <!--====== App Content ======-->
            <div class="app-content">

                <!--====== Section 1 ======-->
                <div class="u-s-p-y-60">

                    <!--====== Section Content ======-->
                    <div class="section__content">
                        <div class="container">
                            <div class="breadcrumb">
                                <div class="breadcrumb__wrap">
                                    <ul class="breadcrumb__list">
                                        <li class="has-separator">

                                            <a href="index.jsp">Home</a></li>
                                        <li class="is-marked">

                                            <a href="dash-my-profile.jsp">My Account</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section 1 ======-->


                <!--====== Section 2 ======-->
                <div class="u-s-p-b-60">

                    <!--====== Section Content ======-->
                    <div class="section__content">
                        <div class="dash">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-3 col-md-12">


                                        <!--====== Dashboard Features ======-->
                                        <%@ include file="dashboard-sidebar.jsp" %>


                                        <div class="dash__box dash__box--bg-white dash__box--shadow dash__box--w">
                                            <div class="dash__pad-1">
                                                <ul class="dash__w-list">
                                                    <li>
                                                        <div class="dash__w-wrap">

                                                            <span class="dash__w-icon dash__w-icon-style-1"><i
                                                                    class="fas fa-cart-arrow-down"></i></span>

                                                            <span class="dash__w-text">4</span>

                                                            <span class="dash__w-name">Orders Placed</span></div>
                                                    </li>
                                                    <li>
                                                        <div class="dash__w-wrap">

                                                            <span class="dash__w-icon dash__w-icon-style-2"><i
                                                                    class="fas fa-times"></i></span>

                                                            <span class="dash__w-text">0</span>

                                                            <span class="dash__w-name">Cancel Orders</span></div>
                                                    </li>
                                                    <li>
                                                        <div class="dash__w-wrap">

                                                            <span class="dash__w-icon dash__w-icon-style-3"><i
                                                                    class="far fa-heart"></i></span>

                                                            <span class="dash__w-text">0</span>

                                                            <span class="dash__w-name">Wishlist</span></div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!--====== End - Dashboard Features ======-->
                                    </div>
                                    <div class="col-lg-9 col-md-12">
                                        <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                            <div class="dash__pad-2">
                                                <h1 class="dash__h1 u-s-m-b-14">My Profile</h1>

                                                <span class="dash__text u-s-m-b-30">Look all your info, you could customize your profile.</span>
                                                <div class="row">
                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">Full Name</h2>

                                                        <span class="dash__text">${currentUser.firstName} ${currentUser.lastName}</span>
                                                    </div>

                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">E-mail</h2>

                                                        <span class="dash__text">${currentUser.email}</span>
                                                    </div>

                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">Phone</h2>

                                                        <span class="dash__text">${currentUser.phone}</span>

                                                    </div>
                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">Birthday</h2>

                                                        <span class="dash__text">${currentUser.birthDate}</span>
                                                    </div>
                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">Gender</h2>

                                                        <span class="dash__text">${currentUser.gender}</span>
                                                    </div>
                                                    <div class="col-lg-4 u-s-m-b-30">
                                                        <h2 class="dash__h2 u-s-m-b-8">Address</h2>

                                                        <span class="dash__text">${currentUser.address}</span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="dash__link dash__link--secondary u-s-m-b-30">
                                                        </div>
                                                        <div class="u-s-m-b-16">

                                                            <a class="dash__custom-link btn--e-transparent-brand-b-2"
                                                               href="dash-edit-profile.html">Edit Profile</a></div>
                                                        <div>

                                                            <a class="dash__custom-link btn--e-brand-b-2" href="#">Change
                                                                Password</a></div>
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
                <!--====== End - Section 2 ======-->
            </div>
            <!--====== End - App Content ======-->


            <!--====== Main Footer ======-->
            <footer>
                <div class="outer-footer">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 col-md-6">
                                <div class="outer-footer__content u-s-m-b-40">

                                    <span class="outer-footer__content-title">Contact Us</span>
                                    <div class="outer-footer__text-wrap"><i class="fas fa-home"></i>

                                        <span>4247 Ashford Drive Virginia VA-20006 USA</span></div>
                                    <div class="outer-footer__text-wrap"><i class="fas fa-phone-volume"></i>

                                        <span>(+0) 900 901 904</span></div>
                                    <div class="outer-footer__text-wrap"><i class="far fa-envelope"></i>

                                        <span>contact@domain.com</span></div>
                                    <div class="outer-footer__social">
                                        <ul>
                                            <li>

                                                <a class="s-fb--color-hover" href="#"><i class="fab fa-facebook-f"></i></a>
                                            </li>
                                            <li>

                                                <a class="s-tw--color-hover" href="#"><i class="fab fa-twitter"></i></a>
                                            </li>
                                            <li>

                                                <a class="s-youtube--color-hover" href="#"><i
                                                        class="fab fa-youtube"></i></a></li>
                                            <li>

                                                <a class="s-insta--color-hover" href="#"><i
                                                        class="fab fa-instagram"></i></a></li>
                                            <li>

                                                <a class="s-gplus--color-hover" href="#"><i
                                                        class="fab fa-google-plus-g"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <div class="outer-footer__content u-s-m-b-40">

                                            <span class="outer-footer__content-title">Information</span>
                                            <div class="outer-footer__list-wrap">
                                                <ul>
                                                    <li>

                                                        <a href="cart.html">Cart</a></li>
                                                    <li>

                                                        <a href="dashboard.html">Account</a></li>
                                                    <li>

                                                        <a href="shop-side-version-2.html">Manufacturer</a></li>
                                                    <li>

                                                        <a href="dash-payment-option.html">Finance</a></li>
                                                    <li>

                                                        <a href="shop-side-version-2.html">Shop</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <div class="outer-footer__content u-s-m-b-40">
                                            <div class="outer-footer__list-wrap">

                                                <span class="outer-footer__content-title">Our Company</span>
                                                <ul>
                                                    <li>

                                                        <a href="about.html">About us</a></li>
                                                    <li>

                                                        <a href="contact.html">Contact Us</a></li>
                                                    <li>

                                                        <a href="index.html">Sitemap</a></li>
                                                    <li>

                                                        <a href="dash-my-order.html">Delivery</a></li>
                                                    <li>

                                                        <a href="shop-side-version-2.html">Store</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-12">
                                <div class="outer-footer__content">

                                    <span class="outer-footer__content-title">Join our Newsletter</span>
                                    <form class="newsletter">
                                        <div class="u-s-m-b-15">
                                            <div class="radio-box newsletter__radio">

                                                <input type="radio" id="male" name="gender">
                                                <div class="radio-box__state radio-box__state--primary">

                                                    <label class="radio-box__label" for="male">Male</label></div>
                                            </div>
                                            <div class="radio-box newsletter__radio">

                                                <input type="radio" id="female" name="gender">
                                                <div class="radio-box__state radio-box__state--primary">

                                                    <label class="radio-box__label" for="female">Female</label></div>
                                            </div>
                                        </div>
                                        <div class="newsletter__group">

                                            <label for="newsletter"></label>

                                            <input class="input-text input-text--only-white" type="text" id="newsletter"
                                                   placeholder="Enter your Email">

                                            <button class="btn btn--e-brand newsletter__btn" type="submit">SUBSCRIBE
                                            </button>
                                        </div>

                                        <span class="newsletter__text">Subscribe to the mailing list to receive updates on promotions, new arrivals, discount and coupons.</span>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="lower-footer">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="lower-footer__content">
                                    <div class="lower-footer__copyright">

                                        <span>Copyright © 2018</span>

                                        <a href="index.html">Reshop</a>

                                        <span>All Right Reserved</span></div>
                                    <div class="lower-footer__payment">
                                        <ul>
                                            <li><i class="fab fa-cc-stripe"></i></li>
                                            <li><i class="fab fa-cc-paypal"></i></li>
                                            <li><i class="fab fa-cc-mastercard"></i></li>
                                            <li><i class="fab fa-cc-visa"></i></li>
                                            <li><i class="fab fa-cc-discover"></i></li>
                                            <li><i class="fab fa-cc-amex"></i></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>

            <!--====== Modal Section ======-->


            <!--====== Unsubscribe or Subscribe Newsletter ======-->
            <div class="modal fade" id="dash-newsletter">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content modal--shadow">
                        <div class="modal-body">
                            <form class="d-modal__form">
                                <div class="u-s-m-b-15">
                                    <h1 class="gl-modal-h1">Newsletter Subscription</h1>

                                    <span class="gl-modal-text">I have read and understood</span>

                                    <a class="d_modal__link" href="dash-my-profile.html">Ludus Privacy Policy</a>
                                </div>
                                <div class="gl-modal-btn-group">

                                    <button class="btn btn--e-brand-b-2" type="submit">SUBSCRIBE</button>

                                    <button class="btn btn--e-grey-b-2" type="button" data-dismiss="modal">CANCEL
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--====== Unsubscribe or Subscribe Newsletter ======-->
            <!--====== End - Modal Section ======-->
        </div>
        <!--====== End - Main App ======-->


        <!--====== Google Analytics: change UA-XXXXX-Y to be your site's ID ======-->
        <script>
            window.ga = function() {
                ga.q.push(arguments)
            };
            ga.q = [];
            ga.l = +new Date;
            ga('create', 'UA-XXXXX-Y', 'auto');
            ga('send', 'pageview')
        </script>
        <script src="https://www.google-analytics.com/analytics.js" async defer></script>

        <!--====== Vendor Js ======-->
        <script src="js/vendor.js"></script>

        <!--====== jQuery Shopnav plugin ======-->
        <script src="js/jquery.shopnav.js"></script>

        <!--====== App ======-->
        <script src="js/app.js"></script>

        <!--====== Noscript ======-->
        <noscript>
            <div class="app-setting">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="app-setting__wrap">
                                <h1 class="app-setting__h1">JavaScript is disabled in your browser.</h1>

                                <span class="app-setting__text">Please enable JavaScript in your browser or upgrade to a JavaScript-capable browser.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </noscript>
    </body>
</html>