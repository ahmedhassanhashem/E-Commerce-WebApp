
  $(document).ready(function() {
    // Get product ID from URL parameter
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');
    
    if (productId) {
      loadProductData(productId);
    } else {

      window.location.href = '/webapp/get-Allproducts.jsp';
    }
    
    $("#file-upload").change(function() {
      const file = this.files[0];
      if (file) {
        const reader = new FileReader();
        
        $("#file-name").text(file.name);
        
        reader.onload = function(e) {
          $("#image-preview").attr("src", e.target.result);
        }
        
        reader.readAsDataURL(file);
      } else {
        $("#file-name").text("No file selected");

        const currentImage = $("#current-image").val();
        if (currentImage) {
          $("#image-preview").attr("src", "webapp/images/product/electronic/" + currentImage);
        } else {
          $("#image-preview").attr("src", "images/product/electronic/default-product.jpg");
        }
      }
    });
    
    $("#edit-product-form").on("submit", function(e) {
      e.preventDefault();
      updateProduct();
    });
  });
  
  function loadProductData(productId) {
    $.ajax({
      url: "getProducts",
      type: "GET",
      data: {
        search: productId
      },
      success: function(response) {
        if (response.success && response.products && response.products.length > 0) {
          const product = response.products[0];
          populateForm(product);
        } else {
          showNotification("Product not found", "error");
          setTimeout(function() {
            window.location.href = '/webapp/get-Allproducts.jsp';
          }, 2000);
        }
      },
      error: function(xhr, status, error) {
        showNotification("Failed to load product data: " + error, "error");
      }
    });
  }
  
  function populateForm(product) {
    $("#product-id").val(product.productId);
    $("#product-name").val(product.name);
    $("#product-price").val(product.price);
    $("#product-stock").val(product.stock);
    $("#product-category").val(product.category);
    $("#product-description").val(product.description);
    $("#current-image").val(product.image);
    $("#file-name").text("Current image: " + (product.image || "None"));
    
    if (product.image) {
      $("#image-preview").attr("src", "images/product/electronic/" + product.image);
    } else {
      $("#image-preview").attr("src", "images/product/electronic/default-product.jpg");
    }
  }
  
  function updateProduct() {
    const formData = new FormData(document.getElementById("edit-product-form"));
    
    $.ajax({
      url: "updateProduct",
      type: "POST",
      data: formData,
      processData: false,  // Important for FormData
      contentType: false,  // Important for FormData
      success: function(response) {
        if (response.success) {
          showNotification("Product updated successfully", "success");
          setTimeout(function() {
            window.location.href = '/webapp/get-Allproducts.jsp';
          }, 1500);
        } else {
          showNotification("Error: " + response.message, "error");
        }
      },
      error: function(xhr, status, error) {
        showNotification("Failed to update product: " + error, "error");
      }
    });
  }
  
  function showNotification(message, type) {
    const notification = $("#notification");
    notification.text(message);
    notification.removeClass().addClass(`notification ${type}`);
    notification.fadeIn();
    
    setTimeout(function() {
      notification.fadeOut();
    }, 3000);
  }
