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
    
    <%-- =============== END LOGOUT =============== --%>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Coffee Shop Admin Dashboard</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <jsp:include page="admin-sidebar.jsp">
                <jsp:param name="activePage" value="dashboard"/>
            </jsp:include>
        </div>
    
        <!-- Main Content -->
        <div class="content">
            <div id="notification" class="notification hide"></div>
    
            <!-- Dashboard Panel -->
            <div id="dashboard-panel" class="panel">
                <div class="panel-header">
                    <h2>Dashboard</h2>
                </div>
                <div class="panel-body">
                   <!-- First row - 3 divs -->
                <div style="display: flex; gap: 20px; margin-bottom: 20px;">
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Products In Stock</h3>
                        <h2 id="product-in-stock">0</h2>
                    </div>
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Products Out of Stock</h3>
                        <h2 id="product-out-stock">0</h2>
                    </div>
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Customers</h3>
                        <h2 id="users-count">0</h2>
                    </div>
                </div>
    
                <!-- Second row - 3 divs -->
                <div style="display: flex; gap: 20px; margin-bottom: 20px;">
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Orders Pending</h3>
                        <h2 id="orders-processing-count">0</h2>
                    </div>
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Orders Completed</h3>
                        <h2 id="orders-completed-count">0</h2>
                    </div>
                    <div style="flex: 1; background: #f5f4f4; color: #ff5500; padding: 30px; border-radius: 5px; text-align: center; border: 2px solid #ff5500;">
                        <h3>Orders Cancelled</h3>
                        <h2 id="orders-cancelled-count">0</h2>
                    </div>
                </div>
    
                   
                                    
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/dashboard.js"></script>
    </body>
    <html>