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
                                <h1 class="dash__h1 u-s-m-b-30">Order Details</h1>

                                <c:choose>
                                    <c:when test="${not empty errorMessage}">
                                        <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                            <div class="dash__pad-2">
                                                <div class="dash-l-r">
                                                    <div>
                                                        <div class="manage-o__text-2 u-c-secondary">${errorMessage}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Order Details: Order # and Total -->
                                        <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                            <div class="dash__pad-2">
                                                <div class="dash-l-r">
                                                    <div>
                                                        <div class="manage-o__text-2 u-c-secondary">Order
                                                            #${order.orderId}</div>
                                                        <div class="manage-o__text u-c-silver">Status:
                                                            <span class="manage-o__text u-c-brand">
                                                                    ${order.status}
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <div class="manage-o__text-2 u-c-silver">
                                                            Total: <span
                                                                class="manage-o__text-2 u-c-secondary">$<fmt:formatNumber
                                                                value="${order.totalPrice}"
                                                                type="currency"
                                                                currencySymbol=""
                                                                minFractionDigits="2"
                                                                maxFractionDigits="2"/></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Shipping Address Section -->
                                        <div class="dash__box dash__box--bg-white dash__box--shadow u-s-m-b-30">
                                            <div class="dash__pad-3">
                                                <h2 class="dash__h2 u-s-m-b-8">Shipping Address</h2><br>
                                                <h2 class="dash__h2 u-s-m-b-8">${order.user.name}</h2>
                                                <span class="dash__text-2">${order.user.address}</span>
                                                <span class="dash__text-2">${order.user.phone}</span>
                                            </div>
                                        </div>

                                        <!-- Items Section (Scrollable Table) -->
                                        <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                            <div class="dash__pad-2">
                                                <div class="manage-o__header u-s-m-b-20">
                                                    <div class="manage-o__icon">
                                                        <i class="fas fa-box u-s-m-r-5"></i>
                                                        <span class="manage-o__text">Items Ordered</span>
                                                    </div>
                                                </div>

                                                <div class="dash__table-wrap gl-scroll">
                                                    <table class="dash__table">
                                                        <thead>
                                                        <tr>
                                                            <th>Item</th>
                                                            <th>Price</th>
                                                            <th>Quantity</th>
                                                            <th>Subtotal</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:choose>
                                                            <c:when test="${not empty order.items}">
                                                                <c:forEach items="${order.items}" var="item">
                                                                    <tr>
                                                                        <td>${item.product.name}</td>
                                                                        <td>$<fmt:formatNumber value="${item.itemPrice}"
                                                                                               type="currency"
                                                                                               currencySymbol=""
                                                                                               minFractionDigits="2"
                                                                                               maxFractionDigits="2"/></td>
                                                                        <td>${item.quantity}</td>
                                                                        <td>$<fmt:formatNumber
                                                                                value="${item.itemPrice * item.quantity}"
                                                                                type="currency" currencySymbol=""
                                                                                minFractionDigits="2"
                                                                                maxFractionDigits="2"/></td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr>
                                                                    <td colspan="4">No items found for this order</td>
                                                                </tr>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Order Actions -->
                                        <c:if test="${order.status == 'PENDING'}">
                                            <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
                                                <div class="dash__pad-2">
                                                    <h2 class="dash__h2 u-s-m-b-10">Order Actions</h2>
                                                    <div class="u-s-m-b-15">
                                                        <form id="cancelOrderForm">
                                                            <input type="hidden" name="orderId"
                                                                   value="${order.orderId}">
                                                            <input type="hidden" name="status" value="CANCELLED">
                                                            <button class="btn btn--e-brand-b-2" type="button"
                                                                    id="cancelOrderBtn">Cancel Order
                                                            </button>
                                                        </form>
                                                        <div id="statusMessage" style="margin-top: 10px;"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

                                    </c:otherwise>
                                </c:choose>
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

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="js/custom-js/cancel-order.js"></script>


</body>
</html>