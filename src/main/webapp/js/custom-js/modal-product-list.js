// Use event delegation for modal triggers

function initializeModals() {
    // Re-initialize any modal plugins or libraries you're using
    // For example, if you're using Bootstrap modals:
    $('.modal').modal('dispose').modal(); // This resets Bootstrap modals

    // Or if you're using a custom modal system, reinitialize it here
}


$(document).on('click', '.quick-look-trigger', function(e) {
    e.preventDefault();

    // Get modal ID from data attribute
    const modalId = $(this).data('modal-id');

    // Retrieve data from the clicked element
    const image = $(this).data('image');
    const name = $(this).data('name');
    const description = $(this).data('description');
    const stock = $(this).data('stock');
    const price = $(this).data('price');

    // Store the price in the global variable for later use
    currentPrice = price;

    // Update the Quick Look modal with the retrieved data
    $('#modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#modal-product-name').text(name);
    $('#modal-product-description').text(description);

    // Update stock status and set the quantity input maximum value
    if (stock > 0) {
        $('#modal-product-stock').text("In Stock")
            .removeClass('pd-detail__left')
            .addClass('pd-detail__stock');
    } else {
        $('#modal-product-stock').text("Out of Stock")
            .removeClass('pd-detail__stock')
            .addClass('pd-detail__left');
    }
    $('#modal-product-stock-input').attr('data-max', stock).val(1);

    // Open the modal
    $(modalId).modal('show');
});


$(document).on('click', '.add-to-cart-trigger', function(e) {
    e.preventDefault();

    // Get modal ID from data attribute
    const modalId = $(this).data('modal-id');

    const image = $(this).data('image');
    const name = $(this).data('name');
    const price = $(this).data('price');

    $('#cart-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#cart-modal-product-name').text(name);
    $('#cart-modal-product-price').text('$' + price);

    // Open the modal
    $(modalId).modal('show');
});

$(document).on('click', '.add-to-wishlist-trigger', function(e) {
    e.preventDefault();

    // Get modal ID from data attribute
    const modalId = $(this).data('modal-id');

    const image = $(this).data('image');
    const name = $(this).data('name');
    const price = $(this).data('price');

    $('#wishlist-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#wishlist-modal-product-name').text(name);
    $('#wishlist-modal-product-price').text('$' + price);

    // Open the modal
    $(modalId).modal('show');
});

$(document).on('click', '.add-to-cart2-trigger', function(e) {
    e.preventDefault();

    // Get modal ID from data attribute
    const modalId = $(this).data('modal-id');

    const quantity = $('#modal-product-stock-input').val();
    const name = $('#modal-product-name').text();

    $('#cart2-modal-product-name').text(name);
    $('#cart2-modal-product-price').text('$' + currentPrice);
    $('#cart2-modal-product-quantity').text('Quantity: ' + quantity);

    // Open the modal
    $(modalId).modal('show');
});

$(document).on('click', '.add-to-wishlist2-trigger', function(e) {
    e.preventDefault();

    // Get modal ID from data attribute
    const modalId = $(this).data('modal-id');

    const name = $('#modal-product-name').text();

    $('#wishlist2-modal-product-name').text(name);
    $('#wishlist2-modal-product-price').text('$' + currentPrice);

    // Open the modal
    $(modalId).modal('show');
});