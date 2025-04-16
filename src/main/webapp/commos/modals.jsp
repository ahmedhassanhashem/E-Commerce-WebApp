<%@ taglib prefix="c" uri="jakarta.tags.core" %>




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

                                        <img id="modal-product-image" class="u-img-fluid" src="" alt=""></div>

                                </div>
                            </div>

                        </div>
                        <!--====== End - Product Detail ======-->
                    </div>
                    <div class="col-lg-7">

                        <!--====== Product Right Side Details ======-->
                        <div class="pd-detail">
                            <div>

                                <span id="modal-product-name" class="pd-detail__name"></span>
                            </div>


                            <div class="u-s-m-b-15">
                                <div class="pd-detail__inline">

                                    <span id="modal-product-stock" class="pd-detail__stock"></span>

<%--                                            <span class="pd-detail__stock">In Stock</span>--%>
<%--                                       --%>
<%--                                            <span class="pd-detail__left">Out of Stock</span>--%>




                                </div>
                            </div>

                            <div class="u-s-m-b-15">

                                <span id="modal-product-description" class="pd-detail__preview-desc"></span></div>

<%--                            <div class="u-s-m-b-15">--%>
<%--                                <div class="pd-detail__inline">--%>

<%--                                    <span class="pd-detail__click-wrap"><i class="far fa-heart u-s-m-r-6"></i></span>--%>

<%--                                    <a data-modal="modal" class="add-to-wishlist2-trigger" data-modal-id="#add-to-wishlist2" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist">Add to Wishlist</a>--%>

<%--                                </div>--%>

<%--                            </div>--%>

<%--                            <div class="u-s-m-b-15">--%>
<%--                                <div class="pd-detail__inline">--%>

<%--                                    <span class="pd-detail__click-wrap"><i class="fas fa-shopping-cart u-s-m-r-6"></i></span>--%>

<%--                                    <a data-modal="modal" class="add-to-cart2-trigger" data-modal-id="#add-to-cart2" data-tooltip="tooltip" data-placement="top" title="Add to Cart">Add to Cart</a>--%>
<%--                                </div>--%>

<%--                            </div>--%>


                            <div class="u-s-m-b-15">
                                <form class="pd-detail__form">
                                    <div class="pd-detail-inline-2">

                                        <c:if test="${sessionScope.user != null}">
                                        <div class="u-s-m-b-15">

                                            <!--====== Input Counter ======-->
                                            <div class="input-counter">

                                                <span class="input-counter__minus fas fa-minus"></span>

                                                <input id="modal-product-stock-input" class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="1">

                                                <span class="input-counter__plus fas fa-plus"></span>
                                            </div>
                                            <!--====== End - Input Counter ======-->
                                        </div>

                                        <div class="u-s-m-b-15">
                                            <button data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top"
                                                    class="add-to-cart-trigger btn btn--e-brand-b-2" type="button"
                                                    data-image="${requestScope.product.image}"
                                                    data-name="${requestScope.product.name}"
                                                    data-price="${requestScope.product.price}">
                                                Add To Cart
                                            </button>
                                        </div>
                                        </c:if>

                                        <div class="u-s-m-b-15">
                                            <button class="btn btn--e-white-brand-shadow" data-dismiss="modal" type="button" >CONTINUE SHOPPING</button>

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

                            <span id="cart2-modal-product-name" class="success__name"></span>

                            <span id="cart2-modal-product-quantity" class="success__quantity" ></span>

                            <span id="cart2-modal-product-price"  class="success__price"></span></div>

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

                                <img id="cart-modal-product-image" class="modal-product-image u-img-fluid" src="" alt=""></div>
                            <div class="success__info-wrap">

                                <span id="cart-modal-product-name" class="modal-product-name success__name"></span>

<%--                                <span id="cart-modal-product-quantity" class="success__quantity"></span>--%>

                                <span id="cart-modal-product-price" class="modal-product-price success__price"></span></div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="s-option">

                            <span class="s-option__text">item added to your cart</span>
                            <div class="s-option__link-box">

                                <a class="s-option__link btn--e-white-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                                <a class="s-option__link btn--e-white-brand-shadow" href="cart.jsp">VIEW CART</a>

                                <a class="s-option__link btn--e-brand-shadow" href="checkout.jsp">PROCEED TO CHECKOUT</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--====== End - Add to Cart Modal ======-->




<!--====== Add to wishlist Success Modal ======-->

<!--====== Add to wishlist Modal 2 ======-->
<%--<div class="modal fade" id="add-to-wishlist2">--%>
<%--    <div class="modal-dialog modal-dialog-centered">--%>
<%--        <div class="modal-content modal-radius modal-shadow">--%>

<%--            <div class="modal-body">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-sm-6 col-sm-12">--%>
<%--                        <div class="success u-s-m-b-12">--%>
<%--                            <div class="success__text-wrap"><i class="fas fa-check"></i>--%>

<%--                                <span>Item is added successfully to wishlist!</span></div>--%>

<%--                            <span id="wishlist2-modal-product-name" class="success__name"></span>--%>

<%--                            <span id="wishlist2-modal-product-price" class="success__price"></span></div>--%>

<%--                        <a class="s-option__link btn--e-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>--%>

<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!--====== End - Add to Cart Modal 2 ======-->

<!--====== End - Add to Cart Success Modal ======-->




<!--====== Add to Wishlist Modal ======-->
<%--<div class="modal fade" id="add-to-wishlist">--%>
<%--    <div class="modal-dialog modal-dialog-centered">--%>
<%--        <div class="modal-content modal-radius modal-shadow">--%>

<%--            <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>--%>
<%--            <div class="modal-body">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-lg-6 col-md-12">--%>
<%--                        <div class="success u-s-m-b-30">--%>
<%--                            <div class="success__text-wrap"><i class="fas fa-check"></i>--%>

<%--                                <span>Item is added successfully!</span></div>--%>
<%--                            <div class="success__img-wrap">--%>

<%--                                <img id="wishlist-modal-product-image" class="u-img-fluid" src="" alt=""></div>--%>
<%--                            <div class="success__info-wrap">--%>

<%--                                <span id="wishlist-modal-product-name" class="success__name"></span>--%>

<%--                                <span id="wishlist-modal-product-price" class="success__price"></span></div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-lg-6 col-md-12">--%>
<%--                        <div class="s-option">--%>

<%--                            <span class="s-option__text">item added to your wishlist</span>--%>
<%--                            <div class="s-option__link-box">--%>

<%--                                <a class="s-option__link btn--e-white-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>--%>

<%--                                <a class="s-option__link btn--e-white-brand-shadow" href="wishlist.jsp">VIEW WISHLIST</a>--%>

<%--                                <a class="s-option__link btn--e-brand-shadow" href="checkout.jsp">PROCEED TO CHECKOUT</a></div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!--====== End - Add to Wishlist Modal ======-->




<!-- Error Modal -->
<div class="modal fade" id="error-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title">Error</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                An error occurred while processing your request.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
