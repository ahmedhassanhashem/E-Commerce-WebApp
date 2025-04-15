$(document).ready(function() {
    // Load dashboard statistics on page load
    loadDashboardData();
    
    // Function to fetch dashboard data from the servlet
    function loadDashboardData() {
        // Log to ensure function is being called
        console.log("Loading dashboard data...");
        
        $.ajax({
            url: 'dashboard-data',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // Log received data for debugging
                console.log("Received dashboard data:", data);
                
                // Update dashboard counters
                $('#product-in-stock').text(data.productsInStock);
                $('#product-out-stock').text(data.productsOutOfStock);
                $('#users-count').text(data.userCount);
                $('#orders-processing-count').text(data.processingOrders);
                $('#orders-completed-count').text(data.completedOrders);
                $('#orders-cancelled-count').text(data.cancelledOrders);
                
                // Show success notification
                showNotification('Dashboard data loaded successfully', 'success');
            },
            error: function(xhr, status, error) {
                // Log error for debugging
                console.error("Error loading dashboard data:", error);
                console.error("Response:", xhr.responseText);
                
                // Show error notification
                showNotification('Error loading dashboard data: ' + error, 'error');
            }
        });
    }
    
    // Function to display notifications
    function showNotification(message, type) {
        const notification = $('#notification');
        notification.text(message);
        notification.removeClass('hide success error warning');
        notification.addClass(type);
        
        // Show notification
        notification.slideDown();
        
        // Auto-hide after 5 seconds
        setTimeout(function() {
            notification.slideUp();
        }, 5000);
    }
});