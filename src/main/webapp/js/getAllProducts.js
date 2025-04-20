$(document).ready(function() {
    loadProducts();
    
    $("#search-input").on("input", debounce(function() {
        loadProducts();
    }, 500));
    
    $("#category-filter").on("change", function() {
        loadProducts();
    });
});

let currentPage = 1;
const rowsPerPage = 4;
let allProducts = [];

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
                allProducts = response.products;
                displayProducts(allProducts);
                setupPagination();
                showPage(1);
            } else {
                showNotification("Error: " + response.message, "error");
                $("#products-table").html('<tr><td colspan="7" class="text-center">Error loading products</td></tr>');
                $("#pagination").html(''); 
            }
        },
        error: function(xhr, status, error) {
            showNotification("Failed to load products: " + error, "error");
            $("#products-table").html('<tr><td colspan="7" class="text-center">Error loading products</td></tr>');
            $("#pagination").html(''); // Clear pagination if error
        }
    });
}

function displayProducts(products) {
    if (products.length === 0) {
        $("#products-table").html('<tr><td colspan="7" class="text-center">No products found</td></tr>');
        $("#pagination").html(''); // Clear pagination if no products
        return;
    }
    
    let tableContent = '';
    
    products.forEach(function(product) {
        let imageUrl = product.image ? 
        (product.image.toLowerCase().endsWith('.jpg') || product.image.toLowerCase().endsWith('.jpeg') ? 
            'images/product/electronic/' + product.image + '.jpeg' : 
            'images/product/electronic/' + product.image + '.jpg') : 
        '/images/preloader.png';

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
                    <button class="btn btn-primary" onclick="editProduct('${product.productId}')">
                        <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="btn btn-danger" onclick="deleteProduct('${product.productId}')">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        `;
    });
    
    $("#products-table").html(tableContent);
}

function setupPagination() {
    const paginationElement = $("#pagination");
    const pageCount = Math.ceil(allProducts.length / rowsPerPage);
    
    paginationElement.html('');
    
    if (pageCount <= 1) {
        return; 
    }
    
    const prevButton = $('<button></button>')
        .html('&laquo;')
        .addClass('pagination-btn')
        .click(function() {
            if (currentPage > 1) {
                currentPage--;
                showPage(currentPage);
            }
        });
    paginationElement.append(prevButton);
    
    for (let i = 1; i <= pageCount; i++) {
        const button = $('<button></button>')
            .text(i)
            .addClass('pagination-btn')
            .click(function() {
                currentPage = i;
                showPage(currentPage);
            });
            
        if (i === currentPage) {
            button.addClass('active');
        }
        
        paginationElement.append(button);
    }
    
    const nextButton = $('<button></button>')
        .html('&raquo;')
        .addClass('pagination-btn')
        .click(function() {
            if (currentPage < pageCount) {
                currentPage++;
                showPage(currentPage);
            }
        });
    paginationElement.append(nextButton);
}

function showPage(page) {
    currentPage = page;
    
    const startIndex = (page - 1) * rowsPerPage;
    const endIndex = Math.min(startIndex + rowsPerPage, allProducts.length);
    
    const currentProducts = allProducts.slice(startIndex, endIndex);
    
    displayProducts(currentProducts);
    
    $('.pagination-btn').removeClass('active');
    $('.pagination-btn').eq(page).addClass('active');
}

function editProduct(productId) {
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
                    loadProducts(); 
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