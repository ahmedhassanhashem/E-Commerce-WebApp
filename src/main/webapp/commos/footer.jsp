<%@ page session="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!--====== Main Footer ======-->
<footer>
    <div class="outer-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="outer-footer__content u-s-m-b-40">
                        <span class="outer-footer__content-title">Information</span>
                        <div class="outer-footer__text-wrap"><i class="fas fa-home"></i>
                            <span>ITI, Smart Village</span></div>
                        <div class="outer-footer__text-wrap"><i class="fas fa-phone-volume"></i>
                            <span>(+20) 123 456 7890</span></div>
                        <div class="outer-footer__text-wrap"><i class="far fa-envelope"></i>
                            <span>supp0rt@iti.gov.eg</span></div>
                        <div class="outer-footer__social">
                            <ul>
                                <li><a class="s-fb--color-hover" href="#"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a class="s-tw--color-hover" href="#"><i class="fab fa-twitter"></i></a></li>
                                <li><a class="s-youtube--color-hover" href="#"><i class="fab fa-youtube"></i></a></li>
                                <li><a class="s-insta--color-hover" href="#"><i class="fab fa-instagram"></i></a></li>
                                <li><a class="s-gplus--color-hover" href="#"><i class="fab fa-google-plus-g"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="outer-footer__content u-s-m-b-40">
                                <span class="outer-footer__content-title">Website</span>
                                <div class="outer-footer__list-wrap">
                                    <ul>

                                        <c:if test="${not empty sessionScope.user}">
                                            <li><a href="cart.jsp">Cart</a></li>
                                            <%--                                            <li><a href="wishlist.jsp">Wishlist</a></li>--%>
                                            <li><a href="my-account.jsp">Account</a></li>
                                        </c:if>

                                            <li><a href="product-list.jsp">Store</a></li>

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="outer-footer__content u-s-m-b-40">
                                <div class="outer-footer__list-wrap">
                                    <span class="outer-footer__content-title">Our Company</span>
                                    <ul>
                                        <li><a href="about.jsp">About us</a></li>
                                        <li><a href="contact.jsp">Contact Us</a></li>
                                        <li><a href="faq.jsp">FAQ</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
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
                            <span>Copyright &copy; 2018</span>

                            <a href="home">hot coffee</a>

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
<!--====== End - Main Footer ======-->