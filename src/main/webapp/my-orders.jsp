<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="/home"/>
</c:if>


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
        <br>
        <div class="u-s-p-b-60">
            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="dash">
                    <div class="container">
                        <div class="row">


                            <div class="col-lg-3 col-md-12">


                                <%@include file="commos/dashboard-sidebar-links.html" %>
                                <jsp:include page="commos/dashboard-sidebar-orders-statistics.jsp"/>


                            </div>


                            <div class="col-lg-9 col-md-12">


                                <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                    <div class="dash__pad-2">
                                        <h1 class="dash__h1 u-s-m-b-14">My Orders</h1>
                                        <span class="dash__text u-s-m-b-30">Here you can see all products that have been delivered.</span>

                                        <div class="m-order__list">
                                            <c:choose>
                                                <c:when test="${not empty sessionScope.user.orders}">
                                                    <c:forEach items="${sessionScope.user.orders}" var="order">
                                                        <div class="m-order__get">
                                                            <div class="manage-o__header u-s-m-b-30">
                                                                <div class="dash-l-r">
                                                                    <div>
                                                                        <div class="manage-o__text-2 u-c-secondary">
                                                                            Order
                                                                            #${order.orderId}
                                                                        </div>
                                                                        <div class="manage-o__text u-c-silver">
                                                                            <!-- Add order date here if available -->
                                                                        </div>
                                                                    </div>
                                                                    <div>
                                                                        <div class="dash__link dash__link--brand">
                                                                            <a href="order-details?orderId=${order.orderId}">VIEW
                                                                                ORDER</a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <!-- Display each item in the order -->
                                                            <c:choose>
                                                                <c:when test="${not empty order.items}">
                                                                    <c:forEach items="${order.items}" var="item">
                                                                        <div class="manage-o__description">
                                                                            <div class="description__container">
                                                                                <div class="description-title">${item.product.name}</div>
                                                                            </div>
                                                                            <div class="description__info-wrap">
                                                                                <div>
                                                                                    <span class="manage-o__text-2 u-c-silver">Quantity:
                                                                                        <span class="manage-o__text-2 u-c-secondary">${item.quantity}</span>
                                                                                    </span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="manage-o__description">
                                                                        <div class="description__container">
                                                                            <div class="description-title">No items
                                                                                found
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>

                                                            <!-- Order summary section (status and total) -->
                                                            <div class="manage-o__description">
                                                                <div class="description__container">
                                                                </div>
                                                                <div class="description__info-wrap"><br>
                                                                    <div>
                                                                        <c:choose>
                                                                            <c:when test="${order.status.name() eq 'ACCEPTED'}">
                                                                                <span class="manage-o__badge badge--shipped">${order.status}</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status.name() eq 'PENDING'}">
                                                                                <span class="manage-o__badge badge--delivered">${order.status}</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status.name() eq 'CANCELLED'}">
                                                                                <span class="manage-o__badge badge--processing">${order.status}</span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="manage-o__badge">${order.status}</span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <div>
                                                                        <span class="manage-o__text-2 u-c-silver">Total:
                                                                            <span class="manage-o__text-2 u-c-secondary">$<fmt:formatNumber
                                                                                    value="${order.totalPrice}"
                                                                                    type="currency" currencySymbol=""
                                                                                    minFractionDigits="2"
                                                                                    maxFractionDigits="2"/></span>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="m-order__get">
                                                        <div class="manage-o__header u-s-m-b-30">
                                                            <div class="dash-l-r">
                                                                <div>
                                                                    <div class="manage-o__text-2 u-c-secondary">No
                                                                        orders found
                                                                    </div>
                                                                    <c:if test="${empty sessionScope.user}">
                                                                        <div class="manage-o__text u-c-silver">(User not
                                                                            logged in)
                                                                        </div>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
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
    </div>
    <!--====== End -App Content ======-->


    <%@include file="commos/footer.jsp" %>

</div>
<!--====== End - Main App  ======-->


<jsp:include page="commos/modals.jsp"/>

<%@include file="commos/script.html" %>

</body>
</html>