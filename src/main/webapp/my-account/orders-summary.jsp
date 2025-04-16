<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Orders Table -->
<div class="dash__box dash__box--shadow dash__box--bg-white dash__box--radius">
    <h2 class="dash__h2 u-s-p-xy-20">RECENT ORDERS</h2>
    <div class="dash__table-wrap gl-scroll">
        <table class="dash__table">
            <thead>
            <tr>
                <th>Order #</th>
                <th>Items</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty sessionScope.user.orders}">
                    <c:forEach items="${sessionScope.user.orders}" var="order">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>
                                <ul class="order-items" style="list-style: none">
                                    <c:forEach items="${order.items}" var="item" varStatus="status">
                                        <li>
                                            <c:out value="${item.product.name}" default="N/A"/>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${empty order.items}">
                                        <li>No items found</li>
                                    </c:if>
                                </ul>
                            </td>
                            <td>
                                <ul class="order-items" style="list-style: none">
                                    <c:forEach items="${order.items}" var="item">
                                        <li>${item.quantity}</li>
                                    </c:forEach>
                                    <c:if test="${empty order.items}">
                                        <li>N/A</li>
                                    </c:if>
                                </ul>
                            </td>
                            <td>$<fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol=""
                                                   minFractionDigits="2" maxFractionDigits="2"/></td>
                            <td>
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
                                <div class="dash__link dash__link--brand">
                                    <a href="order-details?orderId=${order.orderId}">VIEW ORDER</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5" class="text-center">
                            No orders found.
                            <c:if test="${empty sessionScope.user}">
                                (User not logged in)
                            </c:if>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>