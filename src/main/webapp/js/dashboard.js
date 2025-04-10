
    // Global variables
    let users = [];
    let products = [];

    // Initialize the dashboard
    $(document).ready(function() {
    loadUsers();
    loadProducts();
    updateDashboardCounts();
});

    // Load categories
    function loadUsers() {
    $.ajax({
        url: 'CategoryServlet',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            categories = data;
            updateDashboardCounts();
        },
        error: function() {
            showNotification('Error loading categories', 'error');
            // Demo data
            // users = [
            //     { id: 1, name: 'Coffee Beans', description: 'Various coffee beans', productCount: 5 },
            //     { id: 2, name: 'Coffee Mugs', description: 'Stylish mugs', productCount: 3 },
            //     { id: 2, name: 'Coffee Machines', description: 'All the machines and it accessories', productCount: 3 }
            // ];
            updateDashboardCounts();
        }
    });
}

    // Load products
    function loadProducts() {
    $.ajax({
        url: 'ProductServlet',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            products = data;
            renderRecentProducts();
            updateDashboardCounts();
        },
        error: function() {
            showNotification('Error loading products', 'error');
            // // Demo data
            // products = [
            //     { id: 1, name: 'Ethiopian Yirgacheffe', categoryName: 'Coffee Beans', price: 14.99, stock: 50 },
            //     { id: 2, name: 'Colombian Supremo', categoryName: 'Coffee Beans', price: 12.99, stock: 45 }
            // ];
            renderRecentProducts();
            updateDashboardCounts();
        }
    });
}

    // Render recent products on dashboard
    function renderRecentProducts() {
    const tbody = $('#recent-products');
    tbody.empty();

    if (products.length === 0) {
    tbody.append('<tr><td colspan="4">No products found</td></tr>');
    return;
}

    // Get the 5 most recent products
    const recentProducts = products.slice(0, 5);

    recentProducts.forEach(product => {
    tbody.append(`
                <tr>
                    <td>${product.name}</td>
                    <td>${product.categoryName}</td>
                    <td>$${parseFloat(product.price).toFixed(2)}</td>
                    <td>${product.stock}</td>
                </tr>
            `);
});
}

    // Update dashboard counts
    function updateDashboardCounts() {
    $('#product-count').text(products.length);
    $('#users-count').text(users.length);
}

    // Show notification
    function showNotification(message, type) {
    const notification = $('#notification');
    notification.removeClass('hide notification-success notification-error');
    notification.addClass(`notification-${type}`);
    notification.text(message);

    // Hide notification after 3 seconds
    setTimeout(() => {
    notification.addClass('hide');
}, 3000);
}
