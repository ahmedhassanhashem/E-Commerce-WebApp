let currentPage = 1;
const ordersPerPage = 4;
let currentOrders = [];

$(document).ready(function() {
    loadOrders();
    
    $("#search-order").on("input", debounce(function() {
        currentPage = 1; // Reset to first page when searching
        loadOrders();
    }, 500));
    
    $("#filter-status").on("change", function() {
        currentPage = 1; // Reset to first page when filtering
        loadOrders();
    });
});

function loadOrders() {
    const searchTerm = $("#search-order").val();
    const statusFilter = $("#filter-status").val();
    
    $("#orders-table tbody").html('<tr><td colspan="6" class="text-center">Loading orders...</td></tr>');
    
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
                displayOrders();
                displayPagination();
            } else {
                showNotification("Error: " + response.message, "error");
                $("#orders-table tbody").html('<tr><td colspan="6" class="text-center">Error loading orders</td></tr>');
            }
        },
        error: function(xhr, status, error) {
            showNotification("Failed to load orders: " + error, "error");
            $("#orders-table tbody").html('<tr><td colspan="6" class="text-center">Error loading orders</td></tr>');
        }
    });
}

function displayOrders() {
    if (currentOrders.length === 0) {
        $("#orders-table tbody").html('<tr><td colspan="6" class="text-center">No orders found</td></tr>');
        $("#pagination").html(''); // Hide pagination if no orders
        return;
    }
    
    // Calculate pagination indexes
    const startIndex = (currentPage - 1) * ordersPerPage;
    const endIndex = Math.min(startIndex + ordersPerPage, currentOrders.length);
    const paginatedOrders = currentOrders.slice(startIndex, endIndex);
    
    let tableContent = '';
    
    paginatedOrders.forEach(function(order) {
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

function displayPagination() {
    const totalPages = Math.ceil(currentOrders.length / ordersPerPage);
    
    if (totalPages <= 1) {
        $("#pagination").html(''); // Hide pagination if only one page
        return;
    }
    
    let paginationHtml = '';
    
    // Previous button
    paginationHtml += `
        <button class="pagination-btn" ${currentPage === 1 ? 'disabled' : ''} onclick="changePage(${currentPage - 1})">
            <i class="fas fa-chevron-left"></i>
        </button>
    `;
    
    // Page numbers (show max 5 page numbers)
    const maxPageNumbersToShow = 5;
    let startPage = Math.max(1, currentPage - 2);
    let endPage = Math.min(totalPages, startPage + maxPageNumbersToShow - 1);
    
    if (endPage - startPage < maxPageNumbersToShow - 1) {
        startPage = Math.max(1, endPage - maxPageNumbersToShow + 1);
    }
    
    for (let i = startPage; i <= endPage; i++) {
        paginationHtml += `
            <button class="pagination-btn ${currentPage === i ? 'active' : ''}" onclick="changePage(${i})">
                ${i}
            </button>
        `;
    }
    
    // Next button
    paginationHtml += `
        <button class="pagination-btn" ${currentPage === totalPages ? 'disabled' : ''} onclick="changePage(${currentPage + 1})">
            <i class="fas fa-chevron-right"></i>
        </button>
    `;
    
    $("#pagination").html(paginationHtml);
}

function changePage(newPage) {
    if (newPage < 1 || newPage > Math.ceil(currentOrders.length / ordersPerPage)) {
        return; // Invalid page number
    }
    
    currentPage = newPage;
    displayOrders();
    displayPagination();
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
    currentPage = 1; // Reset to first page when refreshing
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