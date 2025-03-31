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




            <!-- page content -->
            <!--====== Section 1 ======-->
            <div class="u-s-p-y-90">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-12">
                            <div class="shop-w-master">
                                <h1 class="shop-w-master__heading u-s-m-b-30"><i class="fas fa-filter u-s-m-r-8"></i>

                                    <span>FILTERS</span></h1>
                                <div class="shop-w-master__sidebar sidebar--bg-snow">

                                    <div class="u-s-m-b-30">
                                        <div class="shop-w">
                                            <div class="shop-w__intro-wrap">
                                                <h1 class="shop-w__h">CATEGORY</h1>

                                                <span class="fas fa-minus collapsed shop-w__toggle" data-target="#s-category" data-toggle="collapse"></span>
                                            </div>
                                            <div class="shop-w__wrap collapse" id="s-category">
                                                <ul class="shop-w__list gl-scroll">
                                                    <li>

                                                        <a href="#">Coffee Beans</a>

                                                        <span class="category-list__text u-s-m-l-6">(0)</span>
                                                    </li>
                                                    <li>

                                                        <a href="#">Coffee Mugs</a>

                                                        <span class="category-list__text u-s-m-l-6">(0)</span>
                                                    </li>
                                                    <li>

                                                        <a href="#">Coffee Machines</a>

                                                        <span class="category-list__text u-s-m-l-6">(0)</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="u-s-m-b-30">
                                        <div class="shop-w">
                                            <div class="shop-w__intro-wrap">
                                                <h1 class="shop-w__h">PRICE</h1>

                                                <span class="fas fa-minus shop-w__toggle" data-target="#s-price" data-toggle="collapse"></span>
                                            </div>
                                            <div class="shop-w__wrap collapse show" id="s-price">
                                                <form class="shop-w__form-p">
                                                    <div class="shop-w__form-p-wrap">
                                                        <div>

                                                            <label for="price-min"></label>

                                                            <input class="input-text input-text--primary-style" type="text" id="price-min" placeholder="Min"></div>
                                                        <div>

                                                            <label for="price-max"></label>

                                                            <input class="input-text input-text--primary-style" type="text" id="price-max" placeholder="Max"></div>
                                                        <div>

                                                            <button class="btn btn--icon fas fa-angle-right btn--e-transparent-platinum-b-2" type="submit"></button></div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>



                                    <div class="u-s-m-b-30">
                                        <div class="shop-w">
                                            <div class="shop-w__intro-wrap">
                                                <h1 class="shop-w__h">COLOR</h1>

                                                <span class="fas fa-minus collapsed shop-w__toggle" data-target="#s-color" data-toggle="collapse"></span>
                                            </div>
                                            <div class="shop-w__wrap collapse" id="s-color">
                                                <ul class="shop-w__list-2">
                                                    <li>
                                                        <div class="list__content">

                                                            <input type="checkbox" checked>

                                                            <span>Light</span>
                                                        </div>

                                                        <span class="shop-w__total-text">(23)</span>
                                                    </li>
                                                    <li>
                                                        <div class="list__content">

                                                            <input type="checkbox">

                                                            <span>Medium (Colorful)</span>
                                                        </div>

                                                        <span class="shop-w__total-text">(2)</span>
                                                    </li>
                                                    <li>
                                                        <div class="list__content">

                                                            <input type="checkbox">

                                                            <span>Dark</span></div>

                                                        <span class="shop-w__total-text">(2)</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>





                                    <div class="u-s-m-b-30">
                                        <div class="shop-w">
                                            <div class="shop-w__intro-wrap">
                                                <h1 class="shop-w__h">SIZE</h1>

                                                <span class="fas fa-minus collapsed shop-w__toggle" data-target="#s-size" data-toggle="collapse"></span>
                                            </div>
                                            <div class="shop-w__wrap collapse" id="s-size">
                                                <ul class="shop-w__list gl-scroll">
                                                    <li>

                                                        <!--====== Check Box ======-->
                                                        <div class="check-box">

                                                            <input type="checkbox" id="small">
                                                            <div class="check-box__state check-box__state--primary">

                                                                <label class="check-box__label" for="small">Small</label></div>
                                                        </div>
                                                        <!--====== End - Check Box ======-->

                                                        <span class="shop-w__total-text">(2)</span>
                                                    </li>
                                                    <li>

                                                        <!--====== Check Box ======-->
                                                        <div class="check-box">

                                                            <input type="checkbox" id="medium">
                                                            <div class="check-box__state check-box__state--primary">

                                                                <label class="check-box__label" for="medium">Medium</label></div>
                                                        </div>
                                                        <!--====== End - Check Box ======-->

                                                        <span class="shop-w__total-text">(4)</span>
                                                    </li>
                                                    <li>

                                                        <!--====== Check Box ======-->
                                                        <div class="check-box">

                                                            <input type="checkbox" id="large">
                                                            <div class="check-box__state check-box__state--primary">

                                                                <label class="check-box__label" for="large">Large</label></div>
                                                        </div>
                                                        <!--====== End - Check Box ======-->

                                                        <span class="shop-w__total-text">(6)</span>
                                                    </li>

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>




















                        <div class="col-lg-9 col-md-12">
                            <div class="shop-p">
                                <div class="shop-p__toolbar u-s-m-b-30">
                                    <div class="shop-p__tool-style">
                                        <div class="tool-style__group u-s-m-b-8">

                                            <span class="js-shop-grid-target is-active">Grid</span>

                                            <span class="js-shop-list-target">List</span></div>
                                        <form>
                                            <div class="tool-style__form-wrap">
                                                <div class="u-s-m-b-8"><select class="select-box select-box--transparent-b-2">
                                                    <option>Show: 8</option>
                                                    <option selected>Show: 12</option>
                                                    <option>Show: 16</option>
                                                    <option>Show: 28</option>
                                                </select>
                                                </div>

                                                <div class="u-s-m-b-8"><select class="select-box select-box--transparent-b-2">
                                                    <option selected>Sort By: Newest Items</option>
                                                    <option>Sort By: Latest Items</option>
                                                    <option>Sort By: Lowest Price</option>
                                                    <option>Sort By: Highest Price</option>
                                                </select></div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="shop-p__collection">
                                    <div class="row is-grid-active">




                                        <div class="col-lg-4 col-md-6 col-sm-6">
                                            <div class="product-m">
                                                <div class="product-m__thumb">

                                                    <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-detail.html">

                                                        <img class="aspect__img" src="images/product/electronic/product7.jpg" alt=""></a>
                                                    <div class="product-m__quick-look">

                                                        <a class="fas fa-search" data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick Look"></a></div>
                                                    <div class="product-m__add-cart">

                                                        <a class="btn--e-brand" data-modal="modal" data-modal-id="#add-to-cart">Add to Cart</a></div>
                                                </div>
                                                <div class="product-m__content">
                                                    <div class="product-m__category">

                                                        <a href="shop-side-version-2.html">Electronics</a></div>
                                                    <div class="product-m__name">

                                                        <a href="product-detail.html">Nikon DSLR Camera 4k</a></div>


                                                    <div class="product-m__price">$125.00

                                                        <div class="product-m__hover">
                                                            <div class="product-m__preview-description">

                                                                <span>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</span></div>
                                                            <div class="product-m__wishlist">

                                                                <a class="far fa-heart" data-tooltip="tooltip" data-placement="top" data-modal="modal" data-modal-id="#add-to-wishlist" title="Add to Wishlist"></a>
                                                                <a class="fa fa-plus-circle" data-tooltip="tooltip" data-placement="top"  href="#" title="Checkout"></a>

                                                        </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>






                                        </div>
                                    </div>










                                    <div class="u-s-p-y-60">

                                        <!--====== Pagination ======-->
                                        <ul class="shop-p__pagination">
                                            <li class="is-active">

                                                <a href="shop-grid-left.html">1</a>
                                            </li>
                                            <li class="is-active">

                                                <a href="shop-grid-left.html">2</a>
                                            </li>
                                            <li>
                                                <a class="fas fa-angle-right" href="shop-grid-left.html"></a>
                                            </li>
                                        </ul>
                                        <!--====== End - Pagination ======-->
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!--====== End - Section 1 ======-->
            </div>





        </div>
        <!--====== End -App Content ======-->


    <%@include file="commos/footer.html"%>

</div>
    <!--====== End - Main App  ======-->





    <jsp:include page="commos/modals.jsp"/>

    <%@include file="commos/script.html"%>

</body>
</html>
