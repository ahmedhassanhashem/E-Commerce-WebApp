<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- =============== LOGOUT LOGIC =============== --%>
<%
// Handle logout logic
if ("true".equals(request.getParameter("logout"))) {
    // 1. Invalidate session
    session.invalidate();
    
    // 2. Clear authentication cache
    response.setHeader("WWW-Authenticate", "Digest realm=\"Admin Authentication\"");
    response.setStatus(401);
    
    // 3. Redirect to admin URL after authentication challenge
    response.setHeader("Refresh", "0; URL=http://localhost:9999/webapp/admin");
    return;
}
%>

<c:set var="activePage" value="${param.activePage}" />

<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>

<div class="sidebar">
    <!-- ... other sidebar content ... -->
    <li class="nav-item">
        <a href="?logout=true"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </li>
</div>
<%-- =============== END LOGOUT =============== --%>

<c:set var="activePage" value="${param.activePage}" />

<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>

<div class="sidebar">
    <div class="sidebar-header">
        <h2><i class="fas fa-coffee"></i>Admin</h2>
    </div>
    <ul class="nav-menu">
        <li class="nav-item ${activePage eq 'dashboard' ? 'active' : ''}">
            <a href="admin-dashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        </li>
        <li class="nav-item ${activePage eq 'products' ? 'active' : ''}">
            <a href="get-Allproducts.jsp"><i class="fas fa-box-open"></i>View Products</a>
        </li>
        <li class="nav-item ${activePage eq 'addProduct' ? 'active' : ''}">
            <a href="add_product.jsp"><i class="fas fa-tags"></i> Add Product</a>
        </li>
        <li class="nav-item ${activePage eq 'orders' ? 'active' : ''}">
            <a href="orders-history.jsp"><i class="fas fa-shopping-cart"></i>View Orders</a>
        </li>
        <li class="nav-item">
            <%-- Add logout parameter to the link --%>
            <a href="?logout=true"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </li>
    </ul>
</div>