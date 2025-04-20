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

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Coffee Shop Admin Dashboard</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link href="css/admin-dashboard.css" rel="stylesheet">
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
        <div id="notification" class="notification hide">Dashboard data loaded successfully</div>
  
        <!-- Dashboard Panel -->
        <div id="dashboard-panel" class="panel">
          <div class="panel-header">
            <h2><i class="fas fa-chart-line"></i> Dashboard Overview</h2>
            <button id="refresh-btn" class="btn"><i class="fas fa-sync-alt"></i> Refresh Data</button>
          </div>
          
          <div class="panel-body">
            <!-- First row of statistics -->
            <div class="stats-grid">
              <div class="stat-card">
                <div class="icon"><i class="fas fa-box-open"></i></div>
                <h3>Products In Stock</h3>
                <h2 id="product-in-stock">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="stock-progress" style="width: 0%"></div>
                </div>
              </div>
              
              <div class="stat-card">
                <div class="icon"><i class="fas fa-exclamation-circle"></i></div>
                <h3>Products Out of Stock</h3>
                <h2 id="product-out-stock">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="out-stock-progress" style="width: 0%"></div>
                </div>
              </div>
              
              <div class="stat-card">
                <div class="icon"><i class="fas fa-users"></i></div>
                <h3>Customers</h3>
                <h2 id="users-count">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="users-progress" style="width: 0%"></div>
                </div>
              </div>
              
              <div class="stat-card">
                <div class="icon"><i class="fas fa-hourglass-half"></i></div>
                <h3>Orders Pending</h3>
                <h2 id="orders-processing-count">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="pending-progress" style="width: 0%"></div>
                </div>
              </div>
              
              <div class="stat-card">
                <div class="icon"><i class="fas fa-check-circle"></i></div>
                <h3>Orders Completed</h3>
                <h2 id="orders-completed-count">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="completed-progress" style="width: 0%"></div>
                </div>
              </div>
              
              <div class="stat-card">
                <div class="icon"><i class="fas fa-times-circle"></i></div>
                <h3>Orders Cancelled</h3>
                <h2 id="orders-cancelled-count">0</h2>
                <div class="progress-container">
                  <div class="progress-bar" id="cancelled-progress" style="width: 0%"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  
  <script src="js/dashboard.js"></script>


</body>
</html>