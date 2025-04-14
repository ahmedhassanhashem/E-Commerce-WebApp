<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="shop-p__collection">
    <div class="row is-grid-active">
        <c:forEach var="product" items="${requestScope.products}">
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="product-m">
                    <div class="product-m__thumb">
                        <a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-details?name=${product.name}">
                            <img class="aspect__img" src="images/product/electronic/${product.image}.jpg" alt="">
                        </a>
                        <div class="product-m__quick-look">
                            <a data-image="${product.image}"
                               data-name="${product.name}"
                               data-description="${product.description}"
                               data-stock="${product.stock}"
                               data-price="${product.price}"
                               class="fas fa-search quick-look-trigger"
                               data-modal="modal"
                               data-modal-id="#quick-look"
                               data-tooltip="tooltip"
                               data-placement="top"
                               title="Quick Look"></a>
                        </div>
                        <c:if test="${sessionScope.user != null}">
                        <div class="product-m__add-cart">
                            <a data-image="${product.image}"
                               data-name="${product.name}"
                               data-stock="${product.stock}"
                               data-price="${product.price}"
                               class="btn--e-brand add-to-cart-trigger"
                               data-modal="modal"
                               data-modal-id="#add-to-cart">Add to Cart</a>
                        </div>
                        </c:if>
                    </div>
                    <div class="product-m__content">
                        <div class="product-m__category">
                            <a href="product-list?category=${product.category.name().toLowerCase()}">${product.category.name()}</a>
                        </div>
                        <div class="product-m__name">
                            <a href="product-details?id=${product.productId}&name=${product.name}">${product.name}</a>
                        </div>
                        <div class="product-m__price">
                            $${product.price}
                            <div class="product-m__hover">
                                <div class="product-m__preview-description">
                                    <span>${product.description}</span>
                                </div>
<%--                                <div class="product-m__wishlist">--%>
<%--                                    <a data-image="${product.image}"--%>
<%--                                       data-name="${product.name}"--%>
<%--                                       data-price="${product.price}"--%>
<%--                                       class="far fa-heart add-to-wishlist-trigger"--%>
<%--                                       data-tooltip="tooltip"--%>
<%--                                       data-placement="top"--%>
<%--                                       data-modal="modal"--%>
<%--                                       data-modal-id="#add-to-wishlist"--%>
<%--                                       title="Add to Wishlist"></a>--%>
<%--                                    <a class="fa fa-plus-circle"--%>
<%--                                       data-tooltip="tooltip"--%>
<%--                                       data-placement="top"--%>
<%--                                       href="checkout.jsp"--%>
<%--                                       title="Checkout"></a>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


    <div class="u-s-p-y-60">
        <!--====== Pagination ======-->
        <ul class="shop-p__pagination">
            <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                <li class="${i == requestScope.currentPage ? 'is-active' : ''}">
                    <a href="#"
                       class="pagination-link"
                       data-page="${i}"
                       data-category="${param.category}"
                       data-price-min="${param.priceMin}"
                       data-price-max="${param.priceMax}"
                       data-search="${param.search}">
                            ${i}
                    </a>
                </li>
            </c:forEach>
        </ul>
        <!--====== End - Pagination ======-->
    </div>

