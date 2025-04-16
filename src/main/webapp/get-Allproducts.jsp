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
  <title>Products - Coffee Shop Admin</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.13/flatpickr.min.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">
  <link href="css/responsive-admin-gui.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <!-- Sidebar -->
  <div class="sidebar">
    <jsp:include page="admin-sidebar.jsp">
      <jsp:param name="activePage" value="products"/>
    </jsp:include>
  </div>

  <!-- Main Content -->
  <div class="content">
    <div id="notification" class="notification"></div>

    <!-- Products Panel -->
    <div class="panel">
      <div class="panel-header">
        <h2>Manage Products</h2>
        <button class="btn btn-primary" onclick="window.location.href='add_product.jsp'">
          <i class="fas fa-plus"></i> Add Product
        </button>
      </div>

      <!-- Filter Section -->
      <div class="filter-section">
        <div class="filter-row">
          <div class="filter-group">
            <label for="category-filter">Category:</label>
            <select id="category-filter" onchange="filterProducts()">
              <option value="">All Categories</option>
              <option value="BEANS">Beans</option>
              <option value="MUGS">Mugs</option>
              <option value="MACHINES">Machines</option>
            </select>
          </div>

          <div class="filter-group">
            <label for="search-input">Search:  </label>
            <input type="text" id="search-input" placeholder="Search products by ID..." onkeyup="filterProducts()">
          </div>
        </div>
      </div>

      <div class="panel-body">
        <table>
          <thead>
          <tr>
            <th>ID</th>
            <th>Image</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody id="products-table"> <!--  id="products-table"-->
          <c:choose>
            <c:when test="${empty products}">
              <tr>
                <td colspan="7">No products found</td>
              </tr>
            </c:when>
            <c:otherwise>
              <c:forEach items="${products}" var="product">
                <tr data-id="${product.productId}" data-category="${product.category}">
                  <td>${product.productId}</td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty product.image}">
                        <img src="images/product/electronic/${product.image}.jpg" class="product-image" alt="${product.name}">
                      </c:when>
                      <c:otherwise>
                        <img src="images/preloader.png" class="product-image" alt="No Image">
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td>${product.name}</td>
                  <td>${product.category}</td>
                  <td>$${product.price}</td>
                  <td>${product.stock}</td>
                  <td class="action-buttons">
                    <button class="btn btn-primary" onclick="editProduct('${product.productId}')">
                      <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="btn btn-danger" onclick="deleteProduct('${product.productId}')">
                      <i class="fas fa-trash"></i>
                    </button>
                  </td>
                </tr>
              </c:forEach>
            </c:otherwise>
          </c:choose>
          </tbody>
        </table>

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
<script src="js/getAllProducts.js"></script>
<script src="js/responsive-admin-gui.js"></script>

</body>
</html>