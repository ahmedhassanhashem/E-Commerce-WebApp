// cart.js - Complete Implementation
document.addEventListener('DOMContentLoaded', () => {
    // Initialize cart UI
    updateCartUIFromStorage();

    // Add to cart button click handlers
    setupAddToCartHandlers();

    // Setup quick look modal handlers
    setupQuickLookHandlers();

    // Event Delegation for dynamic elements
    document.body.addEventListener('click', async (e) => {
        // Remove item handlers
        if(e.target.closest('.remove-item')) {
            const button = e.target.closest('.remove-item');
            e.preventDefault();
            await handleRemoveItem(button);
        }

        // Quantity adjustment handlers
        if(e.target.classList.contains('input-counter__plus')) {
            const input = e.target.previousElementSibling;
            input.value = parseInt(input.value) + 1;
            await handleQuantityChange(input);
        }

        if(e.target.classList.contains('input-counter__minus')) {
            const input = e.target.nextElementSibling;
            if(parseInt(input.value) > 1) {
                input.value = parseInt(input.value) - 1;
                await handleQuantityChange(input);
            }
        }
    });

    // Handle direct input changes for quantity
    document.querySelectorAll('.quantity-input').forEach(input => {
        input.addEventListener('change', async () => {
            await handleQuantityChange(input);
        });

        // Store original value when focusing
        input.addEventListener('focus', () => {
            input.setAttribute('data-old-value', input.value);
        });
    });

    // Clear Cart
    const clearCartBtn = document.getElementById('clear-cart-btn');
    if(clearCartBtn) {
        clearCartBtn.addEventListener('click', async (e) => {
            e.preventDefault();
            await handleClearCart();
        });
    }
});

// --- SETUP FUNCTIONS --- //

// Replace setupAddToCartHandlers() with this:
document.body.addEventListener('click', (e) => {
    if (e.target.closest('.add-to-cart-trigger')) {
        e.preventDefault();
        const button = e.target.closest('.add-to-cart-trigger');
        const productId = button.getAttribute('data-id');
        const quantity = 1; // Default or get from input
        addToCart(productId, quantity, button);
    }
});

function setupQuickLookHandlers() {
    // Setup quick-look modal add to cart button
    const quickLookAddBtn = document.querySelector('#quick-look .add-to-cart');
    if(quickLookAddBtn) {
        quickLookAddBtn.addEventListener('click', async (e) => {
            e.preventDefault();
            const modal = document.getElementById('quick-look');
            const productId = modal.getAttribute('data-product-id');
            const quantityInput = modal.querySelector('.quantity-input');
            const quantity = quantityInput ? parseInt(quantityInput.value) : 1;

            await addToCart(productId, quantity);

            // Close modal after adding to cart
            const bootstrapModal = bootstrap.Modal.getInstance(modal);
            if(bootstrapModal) bootstrapModal.hide();
        });
    }

    // Setup quick-look triggers
    document.querySelectorAll('.quick-look-trigger').forEach(trigger => {
        trigger.addEventListener('click', () => {
            const modal = document.getElementById('quick-look');

            // Set product data in modal
            modal.setAttribute('data-product-id', trigger.getAttribute('data-id'));

            // Populate modal with product details
            modal.querySelector('.modal-product-img').src =
                `images/product/electronic/${trigger.getAttribute('data-image')}.jpg`;
            modal.querySelector('.modal-product-title').textContent =
                trigger.getAttribute('data-name');
            modal.querySelector('.modal-product-description').textContent =
                trigger.getAttribute('data-description');
            modal.querySelector('.modal-product-price').textContent =
                `$${trigger.getAttribute('data-price')}`;

            // Reset quantity to 1
            const quantityInput = modal.querySelector('.quantity-input');
            if(quantityInput) quantityInput.value = 1;
        });
    });
}

// --- CORE CART FUNCTIONS --- //

async function addToCart(productId, quantity, triggerElement = null) {
    try {
        const response = await fetch(contextPath + '/add-to-cart', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                productId: productId,
                quantity: quantity
            })
        });

        if(!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || 'Failed to add item');
        }

        const cartData = await response.json();
        updateCartUI(cartData);

        // Show success message
        if(triggerElement) {
            showAddToCartSuccess(triggerElement);
        } else {
            showAddToCartModal(productId);
        }
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to add item to cart: ' + error.message);
    }
}

async function handleRemoveItem(button) {
    const itemId = button.getAttribute('data-item-id');
    if(!itemId) {
        console.error('No item ID found');
        return;
    }

    try {
        const response = await fetch('/remove-cart-item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ itemId: itemId })
        });

        if(!response.ok) {
            throw new Error('Failed to remove item');
        }

        const cartData = await response.json();
        updateCartUI(cartData);

        // Remove item from UI if we're on the cart page
        const itemRow = button.closest('[data-item-id="' + itemId + '"]');
        if(itemRow) {
            itemRow.remove();
        }
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to remove item from cart');
    }
}

async function handleQuantityChange(input) {
    const itemId = input.getAttribute('data-item-id');
    const quantity = parseInt(input.value);

    if(isNaN(quantity) || quantity < 1) {
        input.value = input.getAttribute('data-old-value') || 1;
        return;
    }

    try {
        const response = await fetch('/update-cart-item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                itemId: itemId,
                quantity: quantity
            })
        });

        if(!response.ok) {
            throw new Error('Failed to update quantity');
        }

        const cartData = await response.json();
        updateCartUI(cartData, false); // Don't update cart page table

        // Update price in cart page if present
        const itemRow = input.closest('[data-item-id="' + itemId + '"]');
        if(itemRow) {
            const priceElement = itemRow.querySelector('.item-total-price');
            if(priceElement && cartData.items) {
                const item = cartData.items.find(i => i.id == itemId);
                if(item) {
                    priceElement.textContent = `$${(item.product.price * item.quantity).toFixed(2)}`;
                }
            }
        }
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to update quantity');
        // Revert to previous value
        input.value = input.getAttribute('data-old-value') || 1;
    }
}

async function handleClearCart() {
    try {
        const response = await fetch('/clear-cart', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        });

        if(!response.ok) {
            throw new Error('Failed to clear cart');
        }

        const result = await response.json();
        if(result.success) {
            // Empty cart UI
            updateCartUI({
                totalItems: 0,
                totalPrice: 0,
                items: []
            });

            // Clear cart table if on cart page
            const cartTable = document.querySelector('.table-p tbody');
            if(cartTable) {
                cartTable.innerHTML = '<tr><td colspan="4" class="text-center">Your cart is empty</td></tr>';
            }
        }
    } catch (error) {
        console.error('Error:', error);
        showErrorModal('Failed to clear cart: ' + error.message);
    }
}

// --- UI UPDATE FUNCTIONS --- //

function updateCartUI(cartData, updateTable = true) {
    // Update cart count everywhere
    document.querySelectorAll('.total-item-round').forEach(el => {
        el.textContent = cartData.totalItems || 0;
    });

    // Update mini-cart dropdown
    updateMiniCart(cartData);

    // Update cart page table if we're on the cart page
    if(updateTable && window.location.pathname.includes('cart.jsp')) {
        updateCartTable(cartData);
    }

    // Update totals
    document.querySelectorAll('.cart-total, .subtotal-value').forEach(el => {
        el.textContent = `$${(cartData.totalPrice || 0).toFixed(2)}`;
    });

    // Save to localStorage for persistence
    localStorage.setItem('cart', JSON.stringify(cartData));
}

function updateMiniCart(cartData) {
    const miniCartContainer = document.querySelector('.mini-product-container');
    if(!miniCartContainer) return;

    if(!cartData.items || cartData.items.length === 0) {
        miniCartContainer.innerHTML = '<div class="text-center p-3">Your cart is empty</div>';
        return;
    }

    miniCartContainer.innerHTML = cartData.items.map(item => `
        <div class="card-mini-product" data-item-id="${item.id}">
            <div class="mini-product">
                <div class="mini-product__image-wrapper">
                    <a class="mini-product__link" href="product-details?id=${item.product.productId}&name=${item.product.name}">
                        <img class="u-img-fluid" src="images/product/electronic/${item.product.image}.jpg" alt="${item.product.name}">
                    </a>
                </div>
                <div class="mini-product__info-wrapper">
                    <span class="mini-product__category">
                        <a href="product-list?category=${item.product.category.toLowerCase()}">${item.product.category}</a>
                    </span>
                    <span class="mini-product__name">
                        <a href="product-details?id=${item.product.productId}&name=${item.product.name}">${item.product.name}</a>
                    </span>
                    <span class="mini-product__quantity">${item.quantity} x</span>
                    <span class="mini-product__price">$${item.product.price.toFixed(2)}</span>
                </div>
            </div>
            <a class="remove-item mini-product__delete-link far fa-trash-alt" data-item-id="${item.id}"></a>
        </div>
    `).join('');
}

function updateCartTable(cartData) {
    const tbody = document.querySelector('.table-p tbody');
    if(!tbody) return;

    if(!cartData.items || cartData.items.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" class="text-center">Your cart is empty</td></tr>';
        return;
    }

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
                        <span class="table-p__name">
                            <a href="product-details?id=${item.product.productId}&name=${item.product.name}">${item.product.name}</a>
                        </span>
                        <span class="table-p__category">
                            <a href="product-list?category=${item.product.category.toLowerCase()}">${item.product.category}</a>
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
                               type="text" 
                               value="${item.quantity}" 
                               data-min="1"
                               data-max="1000"
                               data-item-id="${item.id}">
                        <span class="input-counter__plus fas fa-plus"></span>
                    </div>
                </div>
            </td>
            <td>
                <span class="item-total-price">$${(item.product.price * item.quantity).toFixed(2)}</span>
                <div class="table-p__del-wrap">
                    <a class="remove-item far fa-trash-alt table-p__delete-link" 
                       data-item-id="${item.id}" 
                       href="#"></a>
                </div>
            </td>
        </tr>
    `).join('');

    // Re-initialize quantity inputs
    document.querySelectorAll('.quantity-input').forEach(input => {
        input.addEventListener('focus', () => {
            input.setAttribute('data-old-value', input.value);
        });
    });
}

// --- NOTIFICATION FUNCTIONS --- //

function showAddToCartSuccess(triggerElement) {
    // Create and show a temporary success indicator
    const successIndicator = document.createElement('div');
    successIndicator.className = 'add-success-indicator';
    successIndicator.innerHTML = '<i class="fas fa-check"></i>';
    successIndicator.style.cssText = `
        position: absolute;
        top: -10px;
        right: -10px;
        background-color: #28a745;
        color: white;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 12px;
        opacity: 0;
        transition: opacity 0.3s;
    `;

    triggerElement.style.position = 'relative';
    triggerElement.appendChild(successIndicator);

    // Animate
    setTimeout(() => {
        successIndicator.style.opacity = '1';
        setTimeout(() => {
            successIndicator.style.opacity = '0';
            setTimeout(() => {
                triggerElement.removeChild(successIndicator);
            }, 300);
        }, 1000);
    }, 10);
}

function showAddToCartModal(productId) {
    // This would show a modal with the added product
    console.log('Product added to cart:', productId);
    // Implement modal display if needed
}

function showErrorModal(message) {
    // Create a simple alert for error messages
    alert(message);
}

// --- LOCAL STORAGE SYNC --- //

function updateCartUIFromStorage() {
    const cartData = localStorage.getItem('cart');
    if(cartData) {
        try {
            updateCartUI(JSON.parse(cartData));
        } catch (e) {
            console.error('Error parsing cart data from storage:', e);
            localStorage.removeItem('cart');
        }
    }
}