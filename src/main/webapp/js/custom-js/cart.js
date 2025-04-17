$(document).ready(function() {
    // Function to update mini cart
    function updateMiniCart(cart) {
        $('.total-item-round').text(cart.items.length);
        $('.subtotal-value').text('$' + cart.totalPrice.toFixed(2));
        var itemsHtml = '';
        cart.items.forEach(function(item) {
            itemsHtml += `
            <div class="card-mini-product">
                <div class="mini-product">
                    <div class="mini-product__image-wrapper">
                        <a class="mini-product__link" href="${contextPath}/product-details?id=${item.product.productId}&name=${item.product.name}">
                            <img class="u-img-fluid" src="${contextPath}/images/product/electronic/${item.product.image}.jpg" alt="">
                        </a>
                    </div>
                    <div class="mini-product__info-wrapper">
                        <span class="mini-product__category">
                            <a href="${contextPath}/product-list?category=${item.product.category.toLowerCase()}">${item.product.category}</a>
                        </span>
                        <span class="mini-product__name">
                            <a href="${contextPath}/product-details?id=${item.product.productId}&name=${item.product.name}">${item.product.name}</a>
                        </span>
                        <span class="mini-product__quantity">${item.quantity} x</span>
                        <span class="mini-product__price">$${item.product.price}</span>
                    </div>
                </div>
                <a class="remove-item mini-product__delete-link far fa-trash-alt" data-item-id="${item.id}"></a>
            </div>`;
        });
        $('.mini-product-container').html(itemsHtml);
    }


    // Add to Cart from Modals
    $(document).on('click', '.add-to-cart2-trigger', function(e) {
        e.preventDefault();
        var $button = $(this);
        // var productId = $button.data('product-id') || $button.data('id'); // Fallback for different data attributes
        // var quantity = $('#modal-product-stock-input').val() || 1; // From quick look modal or default to 1
        var productId = $button.attr('data-product-id') || $button.attr('data-id');
        var quantity = $('#modal-product-stock-input').val() || 1;
        var image = $button.attr('data-image');
        var name = $button.attr('data-name');
        var price = $button.attr('data-price');

        console.log('ProductID:', productId, 'Quantity:', quantity, 'Image:', image, 'Name:', name, 'Price:', price);


        if (!productId) {
            alert('Product ID not found');
            return;
        }

        $.ajax({
            url: contextPath + '/add-to-cart',
            type: 'POST',
            data: { productId: productId, quantity: quantity },
            success: function(response) {
                updateMiniCart(response);
                // Show the "Add to Cart" modal
                $('#add-to-cart2').modal('show');
                // Update modal content with the variables we retrieved earlier
                $('#cart2-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
                $('#cart2-modal-product-name').text(name);
                $('#cart2-modal-product-price').text('$' + price);
                $('#cart2-modal-product-quantity').text(quantity + ' x');
            },
            error: function(xhr) {
                alert('Error adding to cart: ' + xhr.responseText);
            }
        });
    });



    $(document).on('click', '.add-to-cart-trigger', function(e) {
        e.preventDefault();
        var $button = $(this);
        
        var productId = $button.attr('data-product-id') || $button.attr('data-id');
        var quantity = $('#modal-product-stock-input').val() || 1;
        var image = $button.attr('data-image');
        var name = $button.attr('data-name');
        var price = $button.attr('data-price');

        console.log('ProductID:', productId, 'Quantity:', quantity, 'Image:', image, 'Name:', name, 'Price:', price);


        if (!productId) {
            alert('Product ID not found');
            return;
        }

        $.ajax({
            url: contextPath + '/add-to-cart',
            type: 'POST',
            data: { productId: productId, quantity: quantity },
            success: function(response) {
                updateMiniCart(response);
                // Show the "Add to Cart" modal
                $('#add-to-cart').modal('show');
                // Update modal content with the variables we retrieved earlier
                $('#cart-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
                $('#cart-modal-product-name').text(name);
                $('#cart-modal-product-price').text('$' + price);
                $('#cart-modal-product-quantity').text(quantity + ' x');
            },
            error: function(xhr) {
                alert('Error adding to cart: ' + xhr.responseText);
            }
        });
    });




    // Update Quantity on Cart Page
    $(document).on('blur', '.input-counter__text', function() {
        var $input = $(this);
        var cartItemId = $input.closest('tr').find('.remove-item').data('item-id') || $input.data('item-id');
        var newQuantity = parseInt($input.val());
        var max = parseInt($input.data('max'));

        if (newQuantity < 1 || newQuantity > max) {
            alert('Quantity must be between 1 and ' + max);
            $input.val($input.data('original-value'));
            return;
        }

        $.ajax({
            url: contextPath + '/update-cart-item',
            type: 'POST',
            data: { cartItemId: cartItemId, quantity: newQuantity },
            success: function(response) {
                updateMiniCart(response);
                if (window.location.pathname.endsWith('cart.jsp')) {
                    location.reload(); // Optional: reload cart page or update UI dynamically
                }
            },
            error: function(xhr) {
                alert('Error updating quantity: ' + xhr.responseText);
            }
        });
    });



    // Store original value for quantity input
    $(document).on('focus', '.input-counter__text', function() {
        $(this).data('original-value', $(this).val());
    });

    // Remove Item
    $(document).on('click', '.remove-item', function(e) {
        e.preventDefault();
        var cartItemId = $(this).attr('data-item-id');
        var $row = $(this).closest('.card-mini-product, tr'); // Works for mini cart and cart page

        $.ajax({
            url: contextPath + '/remove-from-cart',
            type: 'POST',
            data: { cartItemId: cartItemId },
            success: function(response) {
                updateMiniCart(response);
                $row.remove(); // Remove the item from the UI
            },
            error: function(xhr) {
                alert('Error removing item: ' + xhr.responseText);
            }
        });
    });

    // Clear Cart
    $('#clear-cart-btn').on('click', function(e) {
        e.preventDefault();
        if (confirm('Are you sure you want to clear your cart?')) {
            $.ajax({
                url: contextPath + '/clear-cart',
                type: 'POST',
                success: function(response) {
                    updateMiniCart(response);
                    if (window.location.pathname.endsWith('cart.jsp')) {
                        $('.table-p tbody').empty();
                    }
                },
                error: function(xhr) {
                    alert('Error clearing cart: ' + xhr.responseText);
                }
            });
        }
    });
});

