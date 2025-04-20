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
  <title>Customers - Coffee Shop Admin</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.13/flatpickr.min.css" rel="stylesheet">
  <link href="css/getAllUsers.css" rel="stylesheet">


</head>
<body>
<div class="container">
  <!-- Sidebar -->
  <div class="sidebar">
    <jsp:include page="admin-sidebar.jsp">
      <jsp:param name="activePage" value="users"/>
    </jsp:include>
  </div>

  <!-- Main Content -->
  <div class="content">
    <div id="notification" class="notification"></div>

    <!-- Users Panel -->
    <div class="panel">
      <div class="panel-header">
        <h2>Customer Profiles</h2>
      </div>

      <!-- Filter Section -->
      <div class="filter-section">
        <div class="filter-row">
          <div class="filter-group">
            <label for="search-input-id">ID:</label>
            <input type="text" id="search-input-id" name="search-input-id" placeholder="Search Users by ID...">
          </div>
          <div class="filter-group">
            <label for="search-input-email">Email:</label>
            <input type="text" id="search-input-email" name="search-input-email" placeholder="Search Users by Email...">
          </div>
          <div class="filter-group">
            <button id="search-button" class="btn btn-primary">Search</button>
            <button id="reset-button" class="btn btn-secondary">Reset</button>
          </div>
        </div>
      </div>

      <div class="panel-body">
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Credit Balance</th>
              </tr>
            </thead>
            <tbody id="users-table">
              <!-- User data will be loaded here via AJAX -->
            </tbody>
          </table>
        </div>
      
        <!-- Pagination -->
        <div class="pagination" id="pagination">
          <!-- Pagination buttons will be inserted here -->
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.13/flatpickr.min.js"></script>
<script src="js/getAllUsers.js"></script>

</body>
</html>