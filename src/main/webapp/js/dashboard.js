$(document).ready(function() {
    loadDashboardData();
    
    $('#refresh-btn').click(function() {
      loadDashboardData();
    });
    
    function loadDashboardData() {
    
      $.ajax({
        url: 'dashboard-data',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
          console.log("Received dashboard data:", data);
          
          updateDashboard(data);
          
          showNotification('Dashboard data loaded successfully', 'success');
        },
        error: function(xhr, status, error) {
          console.error("Error loading dashboard data:", error);
          console.error("Response:", xhr.responseText);
          
          showNotification('Error loading dashboard data: ' + error, 'error');
        }
      });
    
    }
    
    function updateDashboard(data) {
      // Simple counter animation for numbers
      animateCounter('product-in-stock', 0, data.productsInStock);
      animateCounter('product-out-stock', 0, data.productsOutOfStock);
      animateCounter('users-count', 0, data.userCount);
      animateCounter('orders-processing-count', 0, data.processingOrders);
      animateCounter('orders-completed-count', 0, data.completedOrders);
      animateCounter('orders-cancelled-count', 0, data.cancelledOrders);
      
      const totalProducts = data.productsInStock + data.productsOutOfStock;
      $('#stock-progress').css('width', (data.productsInStock / totalProducts * 100) + '%');
      $('#out-stock-progress').css('width', (data.productsOutOfStock / totalProducts * 100) + '%');
      $('#users-progress').css('width', (data.userCount / 10 * 100) + '%'); // Assuming target is 10 users
      
      const totalOrders = data.processingOrders + data.completedOrders + data.cancelledOrders;
      if (totalOrders > 0) {
        $('#pending-progress').css('width', (data.processingOrders / totalOrders * 100) + '%');
        $('#completed-progress').css('width', (data.completedOrders / totalOrders * 100) + '%');
        $('#cancelled-progress').css('width', (data.cancelledOrders / totalOrders * 100) + '%');
      }
    }
    
    function animateCounter(elementId, startVal, endVal) {
      const duration = 1500;
      const stepTime = 50;
      const steps = duration / stepTime;
      const increment = (endVal - startVal) / steps;
      let current = startVal;
      const element = document.getElementById(elementId);
      
      const timer = setInterval(function() {
        current += increment;
        element.textContent = Math.round(current);
        
        if ((increment > 0 && current >= endVal) || (increment < 0 && current <= endVal)) {
          clearInterval(timer);
          element.textContent = endVal;
        }
      }, stepTime);
    }
    
    function showNotification(message, type) {
      const notification = $('#notification');
      notification.text(message);
      notification.removeClass('hide success error warning');
      notification.addClass(type + ' show');
      
      setTimeout(function() {
        notification.removeClass('show');
        setTimeout(function() {
          notification.addClass('hide');
        }, 500);
      }, 5000);
    }
  });