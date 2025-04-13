// Global variables
let currentPage = 1;
const ordersPerPage = 10;

// Initialize the page
$(document).ready(function() {
    loadOrders();

    // Set up event listeners
    $('#search-order').on('keyup', function() {
        currentPage = 1;
        loadOrders();
    });

    $('#filter-status').on('change', function() {
        currentPage = 1;
        loadOrders();
    });
});

// Load orders from server
function loadOrders() {
    const searchTerm = $('#search-order').val();
    const statusFilter = $('#filter-status').val();

    $.ajax({
        url: 'OrderServlet',
        type: 'GET',
        data: {
            action: 'list',
            search: searchTerm,
            status: statusFilter,
            page: currentPage,
            perPage: ordersPerPage
        },
        dataType: 'json',
        success: function(data) {
            renderOrders(data.orders);
            renderPagination(data.totalOrders);
        },
        error: function() {
            showNotification('Error loading orders', 'error');
            // // For demo purposes, load sample data
            // const sampleData = {
            //     orders: getSampleOrders(),
            //     totalOrders: 25
            // };
            // renderOrders(sampleData.orders);
            // renderPagination(sampleData.totalOrders);
        }
    });
}

// Render orders table
function renderOrders(orders) {
    const tbody = $('#orders-table tbody');
    tbody.empty();

    if (orders.length === 0) {
        tbody.append('<tr><td colspan="7" class="text-center">No orders found</td></tr>');
        return;
    }

    orders.forEach(order => {
        const statusClass = `status-${order.status.toLowerCase()}`;

        let actionButtons = `
            <button class="btn btn-info btn-sm" onclick="viewOrderDetails(${order.id})">
                <i class="fas fa-eye"></i> View
            </button>
        `;

        // Add conditional buttons based on order status
        if (order.status === 'PENDING') {
            actionButtons += `
                <button class="btn btn-success btn-sm" onclick="updateOrderStatus(${order.id}, 'processing')">
                    <i class="fas fa-check"></i> Process
                </button>
            `;
        }

        if (order.status === 'PROCESSING') {
            actionButtons += `
                <button class="btn btn-success btn-sm" onclick="updateOrderStatus(${order.id}, 'completed')">
                    <i class="fas fa-check-circle"></i> Complete
                </button>
            `;
        }

        if (order.status !== 'COMPLETED' && order.status !== 'CANCELLED') {
            actionButtons += `
                <button class="btn btn-danger btn-sm" onclick="updateOrderStatus(${order.id}, 'cancelled')">
                    <i class="fas fa-times"></i> Cancel
                </button>
            `;
        }

        tbody.append(`
            <tr>
                <td>#${order.id}</td>
                <td>${order.customerName}</td>
                <td>${order.itemCount} item(s)</td>
                <td>$${order.totalAmount.toFixed(2)}</td>
                <td><span class="${statusClass}">${order.status}</span></td>
                <td class="action-buttons">
                    ${actionButtons}
                </td>
            </tr>
        `);
    });
}

// Render pagination
function renderPagination(totalOrders) {
    const totalPages = Math.ceil(totalOrders / ordersPerPage);
    const pagination = $('#pagination');
    pagination.empty();

    if (totalPages <= 1) return;

    // Previous button
    pagination.append(`
        <a href="#" onclick="changePage(${currentPage - 1})" ${currentPage == 1 ? 'style="visibility:hidden"' : ''}>
            &laquo;
        </a>
    `);

    // Page numbers
    for (let i = 1; i <= totalPages; i++) {
        pagination.append(`
            <a href="#" onclick="changePage(${i})" ${i == currentPage ? 'class="active"' : ''}>
                ${i}
            </a>
        `);
    }

    // Next button
    pagination.append(`
        <a href="#" onclick="changePage(${currentPage + 1})" ${currentPage == totalPages ? 'style="visibility:hidden"' : ''}>
            &raquo;
        </a>
    `);
}

// Change page
function changePage(page) {
    if (page < 1 || page > Math.ceil($('#pagination a').length - 2)) return;
    currentPage = page;
    loadOrders();
    window.scrollTo(0, 0);
}

// View order details
function viewOrderDetails(orderId) {
    $.ajax({
        url: 'OrderServlet',
        type: 'GET',
        data: { action: 'details', id: orderId },
        dataType: 'json',
        success: function(order) {
            showOrderDetailsModal(order);
        },
        error: function() {
            showNotification('Error loading order details', 'error');
            // // For demo purposes, show sample data
            // const sampleOrder = getSampleOrders().find(o => o.id == orderId);
            // if (sampleOrder) showOrderDetailsModal(sampleOrder);
        }
    });
}

// Show order details modal
function showOrderDetailsModal(order) {
    const modal = $('#orderDetailsModal');
    const statusClass = `status-${order.status.toLowerCase()}`;

    $('#modal-order-id').text(order.id);

    let itemsHtml = '';
    order.items.forEach(item => {
        itemsHtml += `
            <tr>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>$${item.price.toFixed(2)}</td>
                <td>$${(item.price * item.quantity).toFixed(2)}</td>
            </tr>
        `;
    });

    // Create action buttons for the modal
    let modalActionButtons = `
        <button class="btn btn-secondary" onclick="closeModal()">Close</button>
    `;

    // Add conditional buttons based on order status
    if (order.status == 'PENDING') {
        modalActionButtons += `
            <button class="btn btn-success" onclick="updateOrderStatus(${order.id}, 'processing')">
                <i class="fas fa-check"></i> Process Order
            </button>
        `;
    }

    if (order.status == 'PROCESSING') {
        modalActionButtons += `
            <button class="btn btn-success" onclick="updateOrderStatus(${order.id}, 'completed')">
                <i class="fas fa-check-circle"></i> Complete Order
            </button>
        `;
    }

    if (order.status != 'COMPLETED' && order.status != 'CANCELLED') {
        modalActionButtons += `
            <button class="btn btn-danger" onclick="updateOrderStatus(${order.id}, 'cancelled')">
                <i class="fas fa-times"></i> Cancel Order
            </button>
        `;
    }

    $('#order-details-content').html(`
        <div style="margin-bottom: 20px;">
            <h3>Customer Information</h3>
            <p><strong>Name:</strong> ${order.customerName}</p>
            <p><strong>Email:</strong> ${order.customerEmail}</p>
            <p><strong>Phone:</strong> ${order.customerPhone || 'N/A'}</p>
            <p><strong>Address:</strong> ${order.shippingAddress || 'N/A'}</p>
        </div>

        <div style="margin-bottom: 20px;">
            <h3>Order Summary</h3>
            <p><strong>Status:</strong> <span class="${statusClass}">${order.status}</span></p>
            <p><strong>Payment Method:</strong> ${order.paymentMethod || 'N/A'}</p>
            <p><strong>Notes:</strong> ${order.notes || 'N/A'}</p>
        </div>

        <h3>Order Items</h3>
        <table class="order-items">
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                ${itemsHtml}
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
                    <td><strong>$${order.totalAmount.toFixed(2)}</strong></td>
                </tr>
            </tfoot>
        </table>

        <div style="margin-top: 20px; text-align: right;">
            ${modalActionButtons}
        </div>
    `);

    modal.css('display', 'block');
}

// Close modal
function closeModal() {
    $('#orderDetailsModal').css('display', 'none');
}

// Update order status
function updateOrderStatus(orderId, newStatus) {
    if (!confirm(`Are you sure you want to ${newStatus} this order?`)) return;

    $.ajax({
        url: 'OrderServlet',
        type: 'POST',
        data: {
            action: 'update-status',
            id: orderId,
            status: newStatus
        },
        success: function() {
            showNotification(`Order status updated to ${newStatus}`, 'success');
            closeModal();
            loadOrders();
        },
        error: function() {
            showNotification('Error updating order status', 'error');
            // For demo purposes, simulate success
            showNotification(`Order status updated to ${newStatus}`, 'success');
            closeModal();
            loadOrders();
        }
    });
}

// Refresh orders
function refreshOrders() {
    currentPage = 1;
    $('#search-order').val('');
    $('#filter-status').val('all');
    loadOrders();
}

// Show notification
function showNotification(message, type) {
    const notification = $('#notification');
    notification.removeClass('notification-success notification-error');
    notification.addClass(`notification-${type}`);
    notification.text(message).fadeIn();

    setTimeout(() => {
        notification.fadeOut();
    }, 3000);
}