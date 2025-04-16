<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Product - Coffee Shop Admin</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">
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

    <!-- Edit Product Panel -->
    <div class="panel">
      <div class="panel-header">
        <h2><i class="fas fa-edit"></i> Edit Product</h2>
        <button class="btn btn-secondary" onclick="window.location.href='/webapp/get-Allproducts.jsp'">
          <i class="fas fa-arrow-left"></i> Back to Products
        </button>
      </div>

      <div class="panel-body">
        <form id="edit-product-form" enctype="multipart/form-data">
          <input type="hidden" id="product-id" name="productId">
          <input type="hidden" id="current-image" name="currentImage">
          
          <div class="form-group">
            <label for="product-name">Product Name</label>
            <input type="text" id="product-name" name="name" class="form-control" required>
          </div>
          
          <div class="form-row">
            <div class="form-group">
              <label for="product-price">Price ($)</label>
              <input type="number" id="product-price" name="price" class="form-control" step="0.01" min="0" required>
            </div>
            
            <div class="form-group">
              <label for="product-stock">Stock</label>
              <input type="number" id="product-stock" name="stock" class="form-control" min="0" required>
            </div>
            
            <div class="form-group">
              <label for="product-category">Category</label>
              <select id="product-category" name="category" class="form-control" required>
                <option value="BEANS">Beans</option>
                <option value="MUGS">Mugs</option>
                <option value="MACHINES">Machines</option>
              </select>
            </div>
          </div>
          
          <div class="form-group">
            <label for="product-description">Description</label>
            <textarea id="product-description" name="description" class="form-control" rows="4"></textarea>
          </div>
          <!-- <div class="form-group">
            <label for="product-status">Status</label>
            <select id="product-status" name="product-status" class="form-control">
              <option value="ACTIVE">Active</option>
              <option value="OUT_OF_STOCK">Out of Stock</option>
            </select>
          </div> -->
<%--        </div>--%>
          <div class="form-group">
            <label>Product Image</label>
            <div class="file-upload">
              <label for="file-upload" class="custom-file-upload">
                <i class="fas fa-cloud-upload-alt"></i> Choose Image
              </label>
              <input id="file-upload" type="file" name="product-image" accept="image/jpg"/>
              <span id="file-name" class="file-name">No file selected</span>
            </div>
            <small>Select a JPG image for the product</small>
          </div>
          
          <div class="form-group">
            <label>Preview</label>
            <div class="image-preview">
              <img id="image-preview" src="images/product/electronic/default-product.jpg" alt="Product Preview">
            </div>
          </div>
          
          <div class="form-actions">
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-save"></i> Save Changes
            </button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='/webapp/get-Allproducts.jsp'">
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/edit-product.js"></script>
</body>
</html>