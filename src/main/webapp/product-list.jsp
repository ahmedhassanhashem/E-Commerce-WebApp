<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


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

                                                        <span class="category-list__text u-s-m-l-6">(${requestScope.beansCategory})</span>
                                                    </li>
                                                    <li>

                                                        <a href="#">Coffee Mugs</a>

                                                        <span class="category-list__text u-s-m-l-6">(${requestScope.mugsCategory})</span>
                                                    </li>
                                                    <li>

                                                        <a href="#">Coffee Machines</a>

                                                        <span class="category-list__text u-s-m-l-6">(${requestScope.machinesCategory})</span>
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
                                            <div class="shop-w__wrap collapse" id="s-price">
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

                                                            <input type="checkbox" class="color-filter" value="LIGHT">

                                                            <span>Light</span>
                                                        </div>

                                                        <span class="shop-w__total-text">(${requestScope.light})</span>
                                                    </li>
                                                    <li>
                                                        <div class="list__content">

                                                            <input type="checkbox" class="color-filter" value="MEDIUM">

                                                            <span>Medium</span>
                                                        </div>

                                                        <span class="shop-w__total-text">(${requestScope.mediumColor})</span>
                                                    </li>
                                                    <li>
                                                        <div class="list__content">

                                                            <input type="checkbox" class="color-filter" value="DARK">

                                                            <span>Dark</span></div>

                                                        <span class="shop-w__total-text">(${requestScope.dark})</span>
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

                                                        <span class="shop-w__total-text">(${requestScope.small})</span>
                                                    </li>
                                                    <li>

                                                        <!--====== Check Box ======-->
                                                        <div class="check-box">

                                                            <input type="checkbox" id="medium">
                                                            <div class="check-box__state check-box__state--primary">

                                                                <label class="check-box__label" for="medium">Medium</label></div>
                                                        </div>
                                                        <!--====== End - Check Box ======-->

                                                        <span class="shop-w__total-text">(${requestScope.mediumSize})</span>
                                                    </li>
                                                    <li>

                                                        <!--====== Check Box ======-->
                                                        <div class="check-box">

                                                            <input type="checkbox" id="large">
                                                            <div class="check-box__state check-box__state--primary">

                                                                <label class="check-box__label" for="large">Large</label></div>
                                                        </div>
                                                        <!--====== End - Check Box ======-->

                                                        <span class="shop-w__total-text">(${requestScope.large})</span>
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

                                            <span class="js-shop-list-target">List</span>
                                        </div>
                                        <form>
                                            <div class="tool-style__form-wrap">
                                                <div class="u-s-m-b-8"><select  id="showSelect" class="select-box select-box--transparent-b-2">
                                                    <option value="9" selected>Show: 9</option>
                                                    <option value="18" >Show: 18</option>
                                                    <option value="33">Show: 33</option>
                                                </select>
                                                </div>

                                                <div class="u-s-m-b-8"><select id="sortSelect" class="select-box select-box--transparent-b-2">
                                                    <option value="" >Sort By:  </option>
                                                    <option value="lowest" >Sort By: Lowest Price</option>
                                                    <option value="highest">Sort By: Highest Price</option>
                                                </select></div>
                                            </div>
                                        </form>
                                    </div>
                                </div>







                                <!-- Container for product list (AJAX will update this section) -->
                                <div id="productListContainer">
                                    <jsp:include page="product-list-fragment.jsp"/>
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

<script src="js/custom-js/modal-product-list.js"></script>
<script src="js/custom-js/products.js"></script>



</body>
</html>
