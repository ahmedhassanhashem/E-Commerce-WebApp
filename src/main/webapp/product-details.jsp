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

        <!--====== Section 1 ======-->
        <div class="u-s-p-t-90">
            <div class="container">
                <div class="row">



                    <div class="col-lg-5">
                        <!--====== Product Detail Image ======-->
                        <div class="pd u-s-m-b-30">
                            <div class="pd-wrap">
                                <div class="pd-o-img-wrap">
                                    <img class="u-img-fluid" src="images/product/product-d-5.jpg" alt="Product Image">
                                </div>
                            </div>
                        </div>
                    </div>






                    <div class="col-lg-7">
                        <!--====== Product Right Side Details ======-->
                        <div class="pd-detail">
                            <div>

                                <span class="pd-detail__name">Nikon Camera 4k Lens Zoom Pro</span></div>
                            <div>
                                <div class="pd-detail__inline">

                                    <span class="pd-detail__price">$6.99</span>
                                </div>


                                <div class="u-s-m-b-15">
                                    <div class="pd-detail__inline">

                                        <span class="pd-detail__stock">in stock</span>

                                        <span class="pd-detail__left">out of stock</span></div>
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

                                        <span class="pd-detail__click-wrap"><i class="fas fa-shopping-cart u-s-m-r-6"></i></span>

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
                        </div>
                    </div>
                </div>





                <!--====== Product Detail Tab ======-->
                <div class="u-s-p-y-90">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="pd-tab">
                                    <div class="u-s-m-b-30">
                                        <ul class="nav pd-tab__list">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-toggle="tab" href="#pd-desc">DESCRIPTION</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="tab" href="#pd-info">INFORMATION</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="tab-content">

                                        <!--====== Description Tab ======-->
                                        <div class="tab-pane fade show active" id="pd-desc">
                                            <div class="pd-tab__desc">
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</p>
                                            </div>
                                        </div>
                                        <!--====== End - Description Tab ======-->

                                        <!--====== Information Tab ======-->
                                        <div class="tab-pane fade" id="pd-info">
                                            <div class="pd-tab__desc">
                                                <div class="pd-table gl-scroll">
                                                    <table>
                                                        <tbody>
                                                        <tr>
                                                            <td>Category</td>
                                                            <td>Coffee Beans</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Color</td>
                                                            <td>Dark</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <!--====== End - Information Tab ======-->

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Product Detail Tab ======-->







                <div class="u-s-p-b-90">
                    <!--====== Section Intro ======-->
                    <div class="section__intro u-s-m-b-46">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="section__text-wrap">
                                        <h1 class="section__heading u-c-secondary u-s-m-b-12">SIMILAR PRODUCTS</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--====== End - Section Intro ======-->


                    <!--====== Section Content ======-->
                    

















<!--====== Section Content ======-->
<div class="section__content">
    <div class="container">
        <div class="tab-content">
            <!--======  Tab ======-->
            <div class="tab-pane fade show active" id="item">
                <div class="slider-fouc">
                    <div class="owl-carousel tab-slider" data-item="4">

                        <div class="u-s-m-b-30">
                            <div class="product-o product-o--hover-on">
                                <div class="product-o__wrap">

                                    <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details.jsp">

                                        <img class="aspect__img" src="images/product/electronic/product2.jpg" alt=""></a>
                                    <div class="product-o__action-wrap">
                                        <ul class="product-o__action-list">
                                            <li>

                                                <a data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick View"><i class="fas fa-search-plus"></i></a></li>
                                            <li>

                                                <a data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                            </li>

                                            <li>

                                                <a data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"><i class="fas fa-heart"></i></a>
                                            </li>


                                            <li>

                                                <a data-modal="modal" data-modal-id="checkout" data-tooltip="tooltip" data-placement="top" title="Checkout"><i class="fas fa-plus"></i></a></li>

                                        </ul>
                                    </div>
                                </div>

                                <span class="product-o__category">

                                                            <a href="product-list.jsp">Electronics</a></span>

                                <span class="product-o__name">

                                                            <a href="product-details.jsp">Red Wireless Headphone</a></span>


                                <span class="product-o__price">$125.00</span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--====== End -  Tab ======-->
    </div>
</div>























                    <!--====== End - Section Content ======-->
                </div>

                <!--====== End - Section 1 ======-->














            </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.html" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>
