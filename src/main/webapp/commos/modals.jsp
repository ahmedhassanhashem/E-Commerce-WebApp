<%--
  Created by IntelliJ IDEA.
  User: AHMED
  Date: 2025-03-28
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<!--====== Modal Section ======-->


<!--====== Quick Look Modal ======-->
<div class="modal fade" id="quick-look">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal--shadow">

            <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-5">




                        <!--====== Product Detail ======-->
                        <div class="pd u-s-m-b-30">
                            <div class="pd-wrap">
                                <div id="js-product-detail-modal">
                                    <div>

                                        <img class="u-img-fluid" src="images/product/product-d-1.jpg" alt=""></div>

                                </div>
                            </div>

                        </div>
                        <!--====== End - Product Detail ======-->
                    </div>
                    <div class="col-lg-7">

                        <!--====== Product Right Side Details ======-->
                        <div class="pd-detail">
                            <div>

                                <span class="pd-detail__name">Nikon Camera 4k Lens Zoom Pro</span>
                            </div>


                            <div class="u-s-m-b-15">
                                <div class="pd-detail__inline">

                                    <span class="pd-detail__stock">in stock</span>

                                    <span class="pd-detail__left">out of stock</span>
                                </div>
                            </div>

                            <div class="u-s-m-b-15">

                                <span class="pd-detail__preview-desc">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</span></div>

                            <div class="u-s-m-b-15">
                                <div class="pd-detail__inline">

                                    <span class="pd-detail__click-wrap"><i class="far fa-heart u-s-m-r-6"></i></span>

                                    <a data-modal="modal" data-modal-id="#add-to-wishlist2" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist">Add to Wishlist</a>

                                </div>

                            </div>

                            <div class="u-s-m-b-15">
                                <div class="pd-detail__inline">

                                    <span class="pd-detail__click-wrap"><i class="fas fa-shopping-bag u-s-m-r-6"></i></span>

                                    <a data-modal="modal" data-modal-id="#add-to-cart2" data-tooltip="tooltip" data-placement="top" title="Add to Cart">Add to Cart</a>
                                </div>

                            </div>


                            <div class="u-s-m-b-15">
                                <form class="pd-detail__form">
                                    <div class="pd-detail-inline-2">
                                        <div class="u-s-m-b-15">

                                            <!--====== Input Counter ======-->
                                            <div class="input-counter">

                                                <span class="input-counter__minus fas fa-minus"></span>

                                                <input class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="1000">

                                                <span class="input-counter__plus fas fa-plus"></span></div>
                                            <!--====== End - Input Counter ======-->
                                        </div>
                                        <div class="u-s-m-b-15">
                                            <button class="btn btn--e-brand-b-2" type="submit">Checkout</button>
                                        </div>
                                        <div class="u-s-m-b-15">
                                            <button class="btn btn--e-brand-shadow" data-dismiss="modal" type="button" >CONTINUE SHOPPING</button>

                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                        <!--====== End - Product Right Side Details ======-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--====== End - Quick Look Modal ======-->



<!--====== Add to Cart Success Modal ======-->

<!--====== Add to Cart Modal 2 ======-->
<div class="modal fade" id="add-to-cart2">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal-radius modal-shadow">

            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 col-sm-12">
                        <div class="success u-s-m-b-12">
                            <div class="success__text-wrap"><i class="fas fa-check"></i>

                                <span>Item is added successfully to cart!</span></div>

                            <span class="success__name">Beats Bomb Wireless Headphone</span>

                            <span class="success__quantity">Quantity: 1</span>

                            <span class="success__price">$170.00</span></div>

                        <a class="s-option__link btn--e-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!--====== End - Add to Cart Modal 2 ======-->

<!--====== End - Add to Cart Success Modal ======-->




<!--====== Add to Cart Modal ======-->
<div class="modal fade" id="add-to-cart">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal-radius modal-shadow">

            <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="success u-s-m-b-30">
                            <div class="success__text-wrap"><i class="fas fa-check"></i>

                                <span>Item is added successfully!</span></div>
                            <div class="success__img-wrap">

                                <img class="u-img-fluid" src="images/product/electronic/product1.jpg" alt=""></div>
                            <div class="success__info-wrap">

                                <span class="success__name">Beats Bomb Wireless Headphone</span>

                                <span class="success__quantity">Quantity: 1</span>

                                <span class="success__price">$170.00</span></div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="s-option">

                            <span class="s-option__text">1 item (s) in your cart</span>
                            <div class="s-option__link-box">

                                <a class="s-option__link btn--e-white-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                                <a class="s-option__link btn--e-white-brand-shadow" href="cart.html">VIEW CART</a>

                                <a class="s-option__link btn--e-brand-shadow" href="checkout.html">PROCEED TO CHECKOUT</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--====== End - Add to Cart Modal ======-->




<!--====== Add to wishlist Success Modal ======-->

<!--====== Add to Cart Modal 2 ======-->
<div class="modal fade" id="add-to-wishlist2">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal-radius modal-shadow">

            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 col-sm-12">
                        <div class="success u-s-m-b-12">
                            <div class="success__text-wrap"><i class="fas fa-check"></i>

                                <span>Item is added successfully to wishlist!</span></div>

                            <span class="success__name">Beats Bomb Wireless Headphone</span>

                            <span class="success__price">$170.00</span></div>

                        <a class="s-option__link btn--e-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!--====== End - Add to Cart Modal 2 ======-->

<!--====== End - Add to Cart Success Modal ======-->




<!--====== Add to Wishlist Modal ======-->
<div class="modal fade" id="add-to-wishlist">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal-radius modal-shadow">

            <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="success u-s-m-b-30">
                            <div class="success__text-wrap"><i class="fas fa-check"></i>

                                <span>Item is added successfully!</span></div>
                            <div class="success__img-wrap">

                                <img class="u-img-fluid" src="images/product/electronic/product1.jpg" alt=""></div>
                            <div class="success__info-wrap">

                                <span class="success__name">Beats Bomb Wireless Headphone</span>

                                <span class="success__price">$170.00</span></div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="s-option">

                            <span class="s-option__text">1 item (s) in your wishlist</span>
                            <div class="s-option__link-box">

                                <a class="s-option__link btn--e-white-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                                <a class="s-option__link btn--e-white-brand-shadow" href="wishlist.html">VIEW WISHLIST</a>

                                <a class="s-option__link btn--e-brand-shadow" href="checkout.html">PROCEED TO CHECKOUT</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--====== End - Add to Wishlist Modal ======-->


<!--====== Newsletter Subscribe Modal ======-->
<div class="modal fade new-l" id="newsletter-modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content modal--shadow">

            <button class="btn new-l__dismiss fas fa-times" type="button" data-dismiss="modal"></button>
            <div class="modal-body">
                <div class="row u-s-m-x-0">
                    <div class="col-lg-6 new-l__col-1 u-s-p-x-0">

                        <a class="new-l__img-wrap u-d-block" href="shop-side-version-2.html">

                            <img class="u-img-fluid u-d-block" src="images/newsletter/newsletter.jpg" alt=""></a></div>
                    <div class="col-lg-6 new-l__col-2">
                        <div class="new-l__section u-s-m-t-30">
                            <div class="u-s-m-b-8 new-l--center">
                                <h3 class="new-l__h3">Newsletter</h3>
                            </div>
                            <div class="u-s-m-b-30 new-l--center">
                                <p class="new-l__p1">Sign up for emails to get the scoop on new arrivals, special sales and more.</p>
                            </div>
                            <form class="new-l__form">
                                <div class="u-s-m-b-15">

                                    <input class="news-l__input" type="text" placeholder="E-mail Address"></div>
                                <div class="u-s-m-b-15">

                                    <button class="btn btn--e-brand-b-2" type="submit">Sign up!</button></div>
                            </form>
                            <div class="u-s-m-b-15 new-l--center">
                                <p class="new-l__p2">By Signing up, you agree to receive Reshop offers,<br />promotions and other commercial messages. You may unsubscribe at any time.</p>
                            </div>
                            <div class="u-s-m-b-15 new-l--center">

                                <a class="new-l__link" data-dismiss="modal">No Thanks</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--====== End - Newsletter Subscribe Modal ======-->
<!--====== End - Modal Section ======-->
