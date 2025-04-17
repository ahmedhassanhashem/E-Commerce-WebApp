<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.ecommerce.webapp.dao.OrderDAO" %>
<%@ page import="com.ecommerce.webapp.entities.OrderStatus" %>
<%@ page import="com.ecommerce.webapp.entities.User" %>

<%
    // Get user from session
    User user = (User) session.getAttribute("user");
    OrderDAO orderDAO = new OrderDAO();

    // Count orders by status for this specific user using user ID
    long pendingOrders = orderDAO.countByUserAndStatus(user.getUserId(), OrderStatus.PENDING);
    long acceptedOrders = orderDAO.countByUserAndStatus(user.getUserId(), OrderStatus.ACCEPTED);
    long cancelledOrders = orderDAO.countByUserAndStatus(user.getUserId(), OrderStatus.CANCELLED);

    // Store in request scope for JSTL access
    request.setAttribute("pendingOrders", pendingOrders);
    request.setAttribute("acceptedOrders", acceptedOrders);
    request.setAttribute("cancelledOrders", cancelledOrders);
%>

<%-- The rest of the JSP remains the same --%>
<div class="dash__box dash__box--bg-white dash__box--shadow dash__box--w">
    <div class="dash__pad-1">
        <ul class="dash__w-list">
            <li>
                <div class="dash__w-wrap">
                    <span class="dash__w-icon dash__w-icon-style-1"><i class="fas fa-cart-arrow-down"></i></span>
                    <span class="dash__w-text">${pendingOrders}</span>
                    <span class="dash__w-name">Orders Placed</span>
                </div>
            </li>
            <li>
                <div class="dash__w-wrap">
                    <span class="dash__w-icon dash__w-icon-style-2"><i class="fas fa-check"></i></span>
                    <span class="dash__w-text">${acceptedOrders}</span>
                    <span class="dash__w-name">Orders Accepted</span>
                </div>
            </li>
            <li>
                <div class="dash__w-wrap">
                    <span class="dash__w-icon dash__w-icon-style-3"><i class="fas fa-times"></i></span>
                    <span class="dash__w-text">${cancelledOrders}</span>
                    <span class="dash__w-name">Orders Canceled</span>
                </div>
            </li>
        </ul>
    </div>
</div>