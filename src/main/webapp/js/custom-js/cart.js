// Add to Cart
$(document).on('click', '.add-to-cart-trigger', function(e) {
    e.preventDefault();
    const productId = $(this).data('id');
    const quantity = $(this).closest('.input-counter').find('input').val() || 1;

    $.ajax({
        url: contextPath + '/cart/add',
        method: 'POST',
        data: { productId: productId, quantity: quantity },
        success: function(response) {
            updateCartUI(response);
            showSuccessModal('Item added to cart!');
        },
        error: function(xhr) {
            showErrorModal(xhr.responseJSON?.message || 'Error adding to cart');
        }
    });
});

// Update Quantity
$(document).on('click', '.input-counter__minus, .input-counter__plus', function() {
    const input = $(this).siblings('input');
    const cartItemId = input.closest('tr').data('item-id');
    const newQty = parseInt(input.val());

    $.ajax({
        url: contextPath + '/cart/update',
        method: 'POST',
        data: { cartItemId: cartItemId, quantity: newQty },
        success: function(response) {
            updateCartUI(response);
        },
        error: function(xhr) {
            showErrorModal(xhr.responseJSON?.message || 'Error updating cart');
        }
    });
});

// Remove Item
$(document).on('click', '.remove-item', function(e) {
    e.preventDefault();
    const cartItemId = $(this).data('item-id');

    $.ajax({
        url: contextPath + '/cart/remove',
        method: 'POST',
        data: { cartItemId: cartItemId },
        success: function(response) {
            updateCartUI(response);
            $(this).closest('tr').remove();
        },
        error: function(xhr) {
            showErrorModal(xhr.responseJSON?.message || 'Error removing item');
        }
    });
});

// Clear Cart
$('#clear-cart-btn').click(function(e) {
    e.preventDefault();

    $.ajax({
        url: contextPath + '/cart/clear',
        method: 'POST',
        success: function(response) {
            updateCartUI(response);
            $('.cart-item-row').remove();
        },
        error: function(xhr) {
            showErrorModal(xhr.responseJSON?.message || 'Error clearing cart');
        }
    });
});

function updateCartUI(response) {
    $('.total-item-round').text(response.totalItems);
    $('.subtotal-value').text('$' + response.totalPrice);
    $('#cart-total-price').text('$' + response.totalPrice);
}