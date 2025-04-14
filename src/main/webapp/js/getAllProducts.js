$(document).ready(function() {
    // Load products when page loads
    loadProducts();
    
    // Set up event listeners
    $("#search-input").on("input", debounce(function() {
        loadProducts();
    }, 500));
    
    $("#category-filter").on("change", function() {
        loadProducts();
    });
});

// Load products with current filter settings
function loadProducts() {
    const searchTerm = $("#search-input").val();
    const categoryFilter = $("#category-filter").val();
    
    $("#products-table").html('<tr><td colspan="7" class="text-center">Loading products...</td></tr>');
    
    $.ajax({
        url: "getProducts",
        type: "GET",
        data: {
            search: searchTerm,
            category: categoryFilter
        },
        success: function(response) {
            if (response.success) {
                displayProducts(response.products);
            } else {
                showNotification("Error: " + response.message, "error");
                $("#products-table").html('<tr><td colspan="7" class="text-center">Error loading products</td></tr>');
            }
        },
        error: function(xhr, status, error) {
            showNotification("Failed to load products: " + error, "error");
            $("#products-table").html('<tr><td colspan="7" class="text-center">Error loading products</td></tr>');
        }
    });
}

function displayProducts(products) {
    if (products.length === 0) {
        $("#products-table").html('<tr><td colspan="7" class="text-center">No products found</td></tr>');
        return;
    }
    
    let tableContent = '';
    
    products.forEach(function(product) {
        let imageUrl = product.image ? product.image : '/images/preloader.png';
        
        tableContent += `
            <tr data-id="${product.productId}" data-category="${product.category}">
                <td>${product.productId}</td>
                <td>
                    <img src="${imageUrl}" class="product-image" alt="${product.name}">
                </td>
                <td>${product.name}</td>
                <td>${product.category}</td>
                <td>$${product.price.toFixed(2)}</td>
                <td>${product.stock}</td>
                <td class="action-buttons">
                    <button class="btn btn-primary" onclick="editProduct(${product.productId})">
                        <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="btn btn-danger" onclick="deleteProduct(${product.productId})">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        `;
    });
    
    $("#products-table").html(tableContent);
}

function editProduct(productId) {
    // Redirect to edit page with product ID
    window.location.href = `edit_product.jsp?id=${productId}`;
}

function deleteProduct(productId) {
    if (confirm("Are you sure you want to delete this product?")) {
        $.ajax({
            url: "deleteProduct",
            type: "POST",
            data: {
                productId: productId
            },
            success: function(response) {
                if (response.success) {
                    showNotification("Product deleted successfully", "success");
                    loadProducts(); // Refresh the product list
                } else {
                    showNotification("Error: " + response.message, "error");
                }
            },
            error: function(xhr, status, error) {
                showNotification("Failed to delete product: " + error, "error");
            }
        });
    }
}

function filterProducts() {
    loadProducts();
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