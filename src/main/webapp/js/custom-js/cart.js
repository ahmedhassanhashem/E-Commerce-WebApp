// cart.js - Complete Implementation
document.addEventListener('DOMContentLoaded', () => {
    // Initialize cart UI
    updateCartUIFromStorage();

    // Event Delegation for dynamic elements
    document.body.addEventListener('click', async (e) => {
        // Add to Cart handlers
        if(e.target.closest('.add-to-cart-trigger')) {
            const button = e.target.closest('.add-to-cart-trigger');
            e.preventDefault();
            await handleAddToCart(button);
        }

        // Remove item handlers
        if(e.target.closest('.remove-item')) {
            const button = e.target.closest('.remove-item');
            e.preventDefault();
            await handleRemoveItem(button);
        }

        // Quantity input changes
        if(e.target.classList.contains('quantity-input')) {
            await handleQuantityChange(e.target);
        }
    });

    // Clear Cart
    document.getElementById('clear-cart-btn')?.addEventListener('click', handleClearCart);
});

// ------- Core Functions ------- //
async function handleAddToCart(button) {
    const productId = button.dataset.id;
    const quantity = button.dataset.quantity || 1; // Get quantity if available

    try {
        const response = await fetch('/add-to-cart', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                productId,
                quantity
            })
        });

        if(!response.ok) throw new Error('Failed to add item');

        const cartData = await response.json();
        updateCartUI(cartData);
        showAddToCartModal(button.dataset); // Pass product data from button
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to add item to cart');
    }
}



async function handleRemoveItem(button) {
    const itemId = button.dataset.itemId;

    try {
        const response = await fetch('/remove-cart-item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ itemId })
        });

        if(!response.ok) throw new Error('Failed to remove item');

        const cartData = await response.json();
        updateCartUI(cartData);
        button.closest('tr, .card-mini-product')?.remove();
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to remove item from cart');
    }
}

async function handleQuantityChange(input) {
    const itemId = input.dataset.itemId;
    const quantity = input.value;

    try {
        const response = await fetch('/update-cart-item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ itemId, quantity })
        });

        if(!response.ok) throw new Error('Failed to update quantity');

        const cartData = await response.json();
        updateCartUI(cartData, false); // Don't update cart page table
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to update quantity');
        input.value = input.oldValue; // Revert input
    }
}

async function handleClearCart() {
    try {
        const response = await fetch('/clear-cart', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        });

        const result = await response.json();
        if(result.success) {
            updateCartUI({
                totalItems: 0,
                totalPrice: 0,
                items: []
            });
        }
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to clear cart');
    }
}

// ------- UI Update Functions ------- //
function updateCartUI(cartData, updateTable = true) {
    // Update cart count everywhere
    document.querySelectorAll('.total-item-round').forEach(el => {
        el.textContent = cartData.totalItems;
    });

    // Update mini-cart dropdown
    const miniCartContainer = document.querySelector('.mini-product-container');
    if(miniCartContainer) {
        miniCartContainer.innerHTML = cartData.items.map(item => `
            <div class="card-mini-product">
                <img src="images/product/electronic/${item.product.image}.jpg" 
                     alt="${item.product.name}">
                <div class="mini-product__info-wrapper">
                    <span class="mini-product__name">${item.product.name}</span>
                    <span class="mini-product__quantity">${item.quantity} x</span>
                    <span class="mini-product__price">$${item.product.price}</span>
                </div>
                <a class="mini-product__delete-link far fa-trash-alt remove-item" 
                   href="#" data-item-id="${item.id}"></a>
            </div>
        `).join('');
    }

    // Update cart page
    if(updateTable && window.location.pathname.includes('cart.jsp')) {
        updateCartPage(cartData);
    }

    // Update totals
    document.querySelectorAll('.cart-total').forEach(el => {
        el.textContent = `$${cartData.totalPrice.toFixed(2)}`;
    });

    // Sync with localStorage
    localStorage.setItem('cart', JSON.stringify(cartData));
}

function updateCartPage(cartData) {
    const tbody = document.querySelector('.table-p tbody');
    if(!tbody) return;

    tbody.innerHTML = cartData.items.map(item => `
        <tr data-item-id="${item.id}">
            <td>
                <div class="table-p__box">
                    <div class="table-p__img-wrap">
                        <img class="u-img-fluid" 
                             src="images/product/electronic/${item.product.image}.jpg" 
                             alt="${item.product.name}">
                    </div>
                    <div class="table-p__info">
                        <span class="table-p__name">${item.product.name}</span>
                        <span class="table-p__category">
                            ${item.product.category.name()}
                        </span>
                    </div>
                </div>
            </td>
            <td>$${item.product.price.toFixed(2)}</td>
            <td>
                <div class="table-p__input-counter-wrap">
                    <div class="input-counter">
                        <span class="input-counter__minus fas fa-minus"></span>
                        <input class="input-counter__text quantity-input" 
                               type="number" 
                               value="${item.quantity}" 
                               min="1"
                               data-item-id="${item.id}">
                        <span class="input-counter__plus fas fa-plus"></span>
                    </div>
                </div>
            </td>
            <td>
                <a class="remove-item" data-item-id="${item.id}">
                    <i class="far fa-trash-alt"></i>
                </a>
            </td>
        </tr>
    `).join('');
}

// ------- Modal Functions ------- //
function showAddToCartModal(productData) {
    // Update modal content
    document.getElementById('cart-modal-product-image').src =
        `images/product/electronic/${productData.image}.jpg`;
    document.getElementById('cart-modal-product-name').textContent =
        productData.name;
    document.getElementById('cart-modal-product-price').textContent =
        `$${parseFloat(productData.price).toFixed(2)}`;

    // Show modal
    new bootstrap.Modal(document.getElementById('add-to-cart')).show();
}

function showErrorModal(message) {
    const errorModal = document.getElementById('error-modal');
    if(errorModal) {
        errorModal.querySelector('.modal-body').textContent = message;
        new bootstrap.Modal(errorModal).show();
    }
}

// ------- Local Storage Sync ------- //
function updateCartUIFromStorage() {
    const cartData = localStorage.getItem('cart');
    if(cartData) {
        updateCartUI(JSON.parse(cartData));
    }
}

// Initialize quantity input handlers
document.querySelectorAll('.quantity-input').forEach(input => {
    input.addEventListener('focus', () => {
        input.oldValue = input.value;
    });
});