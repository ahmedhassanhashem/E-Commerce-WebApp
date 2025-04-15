


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<%@ include file="commos/head.html" %>
<body class="config" id="js-scrollspy-trigger">

<%@ include file="commos/preloader.html" %>

<!--====== Main App ======-->
<div id="app">
    <jsp:include page="commos/header.jsp"/>

    <!--====== App Content ======-->
    <div class="app-content">
        <!-- Section 1: Product Details -->
        <div class="u-s-p-t-90">
            <div class="container">
                <div class="row">
                    <!-- Product Detail Image -->
                    <div class="col-lg-5">
                        <div class="pd u-s-m-b-30">
                            <div class="pd-wrap">
                                <div class="pd-o-img-wrap">
                                    <img class="u-img-fluid" src="images/product/electronic/${requestScope.product.image}.jpg" alt="Product Image">
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Product Right Side Details -->
                    <div class="col-lg-7">
                        <div class="pd-detail">
                            <div>
                                <span class="pd-detail__name">${requestScope.product.name}</span>
                            </div>
                            <div>
                                <div class="pd-detail__inline">
                                    <span class="pd-detail__price">$${requestScope.product.price}</span>
                                </div>
                                <div class="u-s-m-b-15">
                                    <div class="pd-detail__inline">
                                        <!-- Optionally display stock info if needed -->
                                        <c:choose>
                                            <c:when test="${requestScope.product.stock > 0}">
                                                <span class="pd-detail__stock">In Stock</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="pd-detail__left">Out of Stock</span>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                                <div class="u-s-m-b-15">
                                    <span class="pd-detail__preview-desc">${requestScope.product.description}</span>
                                </div>
<%--                                <div class="u-s-m-b-15">--%>
<%--                                    <div class="pd-detail__inline">--%>
<%--                                        <span class="pd-detail__click-wrap"><i class="far fa-heart u-s-m-r-6"></i></span>--%>
<%--                                        <a data-image="${requestScope.product.image}"--%>
<%--                                           data-name="${requestScope.product.name}"--%>
<%--                                           data-price="${requestScope.product.price}"--%>
<%--                                           class="add-to-wishlist-trigger"--%>
<%--                                           data-modal="modal" data-modal-id="#add-to-wishlist" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist">--%>
<%--                                            Add to Wishlist--%>
<%--                                        </a>--%>
<%--                                    </div>--%>
<%--                                </div>--%>


<%--                                <div class="u-s-m-b-15">--%>
<%--                                    <div class="pd-detail__inline">--%>
<%--                                        <span class="pd-detail__click-wrap"><i class="fas fa-shopping-cart u-s-m-r-6"></i></span>--%>
<%--                                        <a class="add-to-cart-trigger"--%>
<%--                                           data-image="${requestScope.product.image}"--%>
<%--                                           data-name="${requestScope.product.name}"--%>
<%--                                           data-stock="${requestScope.product.stock}"--%>
<%--                                           data-price="${requestScope.product.price}"--%>
<%--                                           data-modal="modal" data-modal-id="#add-to-cart" data-tooltip="tooltip" data-placement="top" title="Add to Cart">--%>
<%--                                            Add to Cart--%>
<%--                                        </a>--%>
<%--                                    </div>--%>
<%--                                </div>--%>


                                <div class="u-s-m-b-15">
                                    <form class="pd-detail__form">
                                        <div class="pd-detail-inline-2">


                                            <c:if test="${sessionScope.user} != null">
                                            <div class="u-s-m-b-15">
                                                <!-- Input Counter -->
                                                <div class="input-counter">
                                                    <span class="input-counter__minus fas fa-minus"></span>
                                                    <input class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="${requestScope.product.stock}">
                                                    <span class="input-counter__plus fas fa-plus"></span>
                                                </div>
                                            </div>
                                            <div class="u-s-m-b-15">
                                                <button class="btn btn--e-brand-b-2" type="submit">Add To Cart</button>
                                            </div>

                                            </c:if>

                                            <div class="u-s-m-b-15">
                                                <button class="btn btn--e-white-brand-shadow"  type="button" onclick="window.location.href='/webapp/product-list'">CONTINUE SHOPPING</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>

                <!-- Product Detail Tab -->
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
                                        <!-- Description Tab -->
                                        <div class="tab-pane fade show active" id="pd-desc">
                                            <div class="pd-tab__desc">
                                                <p>${requestScope.product.description}</p>
                                            </div>
                                        </div>
                                        <!-- Information Tab -->
                                        <div class="tab-pane fade" id="pd-info">
                                            <div class="pd-tab__desc">
                                                <div class="pd-table gl-scroll">
                                                    <table>
                                                        <tbody>
                                                        <tr>
                                                            <td>Category</td>
                                                            <td>Coffee ${requestScope.product.category.name()}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Name</td>
                                                            <td>${requestScope.product.name}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Price</td>
                                                            <td>$${requestScope.product.price}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- End Tab Content -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Product Detail Tab -->

                <!-- Similar Products Section -->
                <div class="u-s-p-b-90">
                    <!-- Section Intro -->
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
                    <!-- End Section Intro -->

                    <!-- Section Content -->
                    <div class="section__content">
                        <div class="container">
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="item">
                                    <div class="slider-fouc">
                                        <div class="owl-carousel tab-slider" data-item="4">
                                            <c:forEach var="similarProduct" items="${requestScope.similarProducts}">
                                                <div class="u-s-m-b-30">
                                                    <div class="product-o product-o--hover-on">
                                                        <div class="product-o__wrap">
                                                            <!-- Use the loop variable "similarProduct" here -->
                                                            <a class="aspect aspect--bg-grey aspect--square u-d-block product-detail-link"
                                                               href="product-details?name=${similarProduct.name}"
                                                               data-name="${similarProduct.name}"
                                                               data-image="${similarProduct.image}"
                                                               data-price="${similarProduct.price}"
                                                               data-description="${similarProduct.description}"
                                                               data-stock="${similarProduct.stock}">
                                                                <img class="aspect__img" src="images/product/electronic/${similarProduct.image}.jpg" alt="">
                                                            </a>
                                                            <div class="product-o__action-wrap">
                                                                <ul class="product-o__action-list">
                                                                    <li>
                                                                        <a class="quick-look-trigger"
                                                                           data-image="${similarProduct.image}"
                                                                           data-name="${similarProduct.name}"
                                                                           data-description="${similarProduct.description}"
                                                                           data-stock="${similarProduct.stock}"
                                                                           data-price="${similarProduct.price}"
                                                                           data-modal="modal" data-modal-id="#quick-look"
                                                                           data-tooltip="tooltip" data-placement="top"
                                                                           title="Quick View"><i class="fas fa-search-plus"></i></a>
                                                                    </li>
                                                                    <c:if test="${sessionScope.user} != null">
                                                                    <li>
                                                                        <a class="add-to-cart-trigger"
                                                                           data-image="${similarProduct.image}"
                                                                           data-name="${similarProduct.name}"
                                                                           data-stock="${similarProduct.stock}"
                                                                           data-price="${similarProduct.price}"
                                                                           data-modal="modal" data-modal-id="#add-to-cart"
                                                                           data-tooltip="tooltip" data-placement="top"
                                                                           title="Add to Cart"><i class="fas fa-shopping-cart"></i></a>
                                                                    </li>
                                                                    </c:if>
<%--                                                                    <li>--%>
<%--                                                                        <a class="add-to-wishlist-trigger"--%>
<%--                                                                           data-image="${similarProduct.image}"--%>
<%--                                                                           data-name="${similarProduct.name}"--%>
<%--                                                                           data-price="${similarProduct.price}"--%>
<%--                                                                           data-modal="modal" data-modal-id="#add-to-wishlist"--%>
<%--                                                                           data-tooltip="tooltip" data-placement="top"--%>
<%--                                                                           title="Add to Wishlist"><i class="fas fa-heart"></i></a>--%>
<%--                                                                    </li>--%>
<%--                                                                    <li>--%>
<%--                                                                        <a data-modal="modal" data-modal-id="checkout"--%>
<%--                                                                           data-tooltip="tooltip" data-placement="top"--%>
<%--                                                                           title="Checkout"><i class="fas fa-plus"></i></a>--%>
<%--                                                                    </li>--%>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <span class="product-o__category">
                                                              <a href="product-list?category=${similarProduct.category.name().toLowerCase()}">
                                                                      ${similarProduct.category.name()}
                                                              </a>
                                                          </span>
                                                        <span class="product-o__name">
                                                              <a class="product-detail-link"
                                                                 href="product-details?name=${similarProduct.name}"
                                                                 data-name="${similarProduct.name}"
                                                                 data-image="${similarProduct.image}"
                                                                 data-price="${similarProduct.price}"
                                                                 data-description="${similarProduct.description}"
                                                                 data-stock="${similarProduct.stock}">
                                                                      ${similarProduct.name}
                                                              </a>
                                                          </span>
                                                        <span class="product-o__price">$${similarProduct.price}</span>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- End Tab Content -->
                        </div>
                    </div>
                    <!-- End Section Content -->
                </div>
                <!-- End Similar Products Section -->
            </div>
        </div>
        <!-- End Section 1 -->
    </div>
    <!-- End App Content -->

    <%@ include file="commos/footer.jsp" %>
</div>
<!-- End Main App -->

<jsp:include page="commos/modals.jsp"/>
<%@ include file="commos/script.html" %>
<script src="js/custom-js/modals.js"></script>
</body>
</html>
