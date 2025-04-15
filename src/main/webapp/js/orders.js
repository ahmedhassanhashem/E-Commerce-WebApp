let currentOrders = [];

$(document).ready(function() {
    loadOrders();
    
    $("#search-order").on("input", debounce(function() {
        loadOrders();
    }, 500));
    
    $("#filter-status").on("change", function() {
        loadOrders();
    });
});

function loadOrders() {
    const searchTerm = $("#search-order").val();
    const statusFilter = $("#filter-status").val();
    
    $("#orders-table tbody").html('<tr><td colspan="7" class="text-center">Loading orders...</td></tr>');
    
    $.ajax({
        url: "getOrders",
        type: "GET",
        data: {
            search: searchTerm,
            status: statusFilter
        },
        success: function(response) {
            if (response.success) {
                currentOrders = response.orders;
                displayOrders(response.orders);
            } else {
                showNotification("Error: " + response.message, "error");
                $("#orders-table tbody").html('<tr><td colspan="7" class="text-center">Error loading orders</td></tr>');
            }
        },
        error: function(xhr, status, error) {
            showNotification("Failed to load orders: " + error, "error");
            $("#orders-table tbody").html('<tr><td colspan="7" class="text-center">Error loading orders</td></tr>');
        }
    });
}

function displayOrders(orders) {
    if (orders.length === 0) {
        $("#orders-table tbody").html('<tr><td colspan="7" class="text-center">No orders found</td></tr>');
        return;
    }
    
    let tableContent = '';
    
    orders.forEach(function(order) {
        tableContent += `
            <tr>
                <td>#${order.orderId}</td>
                <td>${order.customerEmail}</td>
                <td>${order.itemCount} items</td>
                <td>$${order.totalPrice.toFixed(2)}</td>
                <td><span class="status-label status-${order.status.toLowerCase()}">${order.status}</span></td>
                <td>
                    ${order.status === 'PENDING' ? 
                      `<button class="btn btn-sm btn-success" onclick="updateOrderStatus(${order.orderId}, 'ACCEPTED')">
                          <i class="fas fa-check"></i> Accept
                       </button>
                       <button class="btn btn-sm btn-danger" onclick="updateOrderStatus(${order.orderId}, 'CANCELLED')">
                          <i class="fas fa-times"></i> Cancel
                       </button>` : ''}
                </td>
            </tr>
        `;
    });
    
    $("#orders-table tbody").html(tableContent);
}
function updateOrderStatus(orderId, newStatus) {
    $.ajax({
        url: "updateOrderStatus",
        type: "POST",
        data: {
            orderId: orderId,
            status: newStatus
        },
        success: function(response) {
            if (response.success) {
                showNotification(`Order #${orderId} status updated to ${newStatus}`, "success");
                loadOrders(); 
            } else {
                showNotification(`Error: ${response.message}`, "error");
            }
        },
        error: function(xhr, status, error) {
            showNotification(`Failed to update order: ${error}`, "error");
        }
    });
}

function refreshOrders() {
    loadOrders();
    showNotification("Orders refreshed", "success");
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

// Debounce function to prevent excessive API calls
function debounce(func, wait) {
    let timeout;
    return function() {
        const context = this;
        const args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(function() {
            func.apply(context, args);
        }, wait);
    };
}