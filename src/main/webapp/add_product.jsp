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
  <title>Add Product - Coffee Shop Admin</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">
  <link href="css/responsive-admin-gui.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <!-- Sidebar -->
  <div class="sidebar">
    <jsp:include page="admin-sidebar.jsp">
      <jsp:param name="activePage" value="addProduct"/>
    </jsp:include>
  </div>
  <!-- Main Content -->
  <div class="content">
    <div id="notification" class="notification"></div>

    <div class="panel">
      <div class="panel-header">
        <h2><i class="fas fa-plus"></i> Add New Product</h2>
      </div>

      <div class="panel-body">
        <form id="add-product-form" method="post" action="AddProduct" enctype="multipart/form-data">
          <input type="hidden" name="action" value="add">

          <div class="form-section">
            <div class="form-group">
              <label for="product-name" class="required-field">Product Name</label>
              <input type="text" id="product-name" name="product-name" class="form-control" required
                     placeholder="e.g., Colombian Dark Roast Beans">
            </div>

            <div class="form-group">
              <label for="product-category" class="required-field">Category</label>
              <select id="product-category" name="product-category" class="form-control" required>
                <option value="">Select a category</option>
                <option value="Beans">Coffee Beans</option>
                <option value="Mugs">Coffee Mugs</option>
                <option value="Machines">Coffee Machines</option>
              </select>
            </div>

            <div class="form-group">
              <label for="product-price" class="required-field">Price ($)</label>
              <input type="number" id="product-price" name="product-price" class="form-control" step="0.01" min="0" required
                     placeholder="e.g., 12.99">
            </div>

            <div class="form-group">
              <label for="product-stock" class="required-field">Stock Quantity</label>
              <input type="number" id="product-stock" name="product-stock" class="form-control" min="0" required
                     placeholder="e.g., 50">
            </div>

            <div class="form-group form-section-full">
              <label for="product-description">Description</label>
              <textarea id="product-description" name="product-description" class="form-control" rows="5"
                        placeholder="Enter a detailed description of the product..."></textarea>
            </div>

            <div class="form-group form-section-full">
              <label for="product-image">Product Image</label>
              <div class="file-input-container">
                <div class="file-input-button">
                  <i class="fas fa-upload"></i> Choose Image
                </div>
                <input type="file" id="product-image" name="product-image" accept="image/jpg" onchange="previewImage(this)">
                <span class="file-name" id="file-name-display">No file chosen</span>
              </div>
              <div class="image-preview" id="image-preview">
                <span class="image-preview-text">Image Preview</span>
                <img id="preview-img" src="#" alt="Preview">
              </div>
            </div>

            <div class="form-group">
              <label for="product-status">Status</label>
              <select id="product-status" name="product-status" class="form-control">
                <option value="ACTIVE">Active</option>
                <option value="OUT_OF_STOCK">Out of Stock</option>
              </select>
            </div>
          </div>

          <div class="btn-group">
            <button type="button" class="btn btn-secondary" onclick="window.location.href='admin-dashboard.jsp'">Cancel</button>
            <button type="submit" class="btn btn-success">Save Product</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/add_product.js"></script>
<script src="js/responsive-admin-gui.js"></script>

</body>
</html>