// Declare a global variable to hold the current product price
var currentPrice = 0;

// Quick Look Trigger: Update the Quick Look modal with product details
$(document).on('click', '.quick-look-trigger', function(e) {
    e.preventDefault();

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
});

// Add to Cart Trigger: Update the Add to Cart modal with product details
$(document).on('click', '.add-to-cart-trigger', function(e) {
    e.preventDefault();

    // Retrieve data from the clicked element
    const image = $(this).data('image');
    const name = $(this).data('name');
    const price = $(this).data('price');

    // Update the Add to Cart modal with the retrieved data
    $('#cart-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#cart-modal-product-name').text(name);
    $('#cart-modal-product-price').text('$' + price);
});

// Add to Wishlist Trigger: Update the Add to Wishlist modal with product details
$(document).on('click', '.add-to-wishlist-trigger', function(e) {
    e.preventDefault();

    // Retrieve data from the clicked element
    const image = $(this).data('image');
    const name = $(this).data('name');
    const price = $(this).data('price');

    // Update the Add to Wishlist modal with the retrieved data
    $('#wishlist-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#wishlist-modal-product-name').text(name);
    $('#wishlist-modal-product-price').text('$' + price);
});

// Add to Cart2 Trigger: Use the Quick Look modal data and chosen quantity for Add to Cart2 modal
$(document).on('click', '.add-to-cart2-trigger', function(e) {
    e.preventDefault();

    // Read the chosen quantity from the Quick Look modal input
    const quantity = $('#modal-product-stock-input').val();
    const name = $('#modal-product-name').text();

    // Update the Add to Cart2 modal content with the retrieved data
    $('#cart2-modal-product-name').text(name);
    $('#cart2-modal-product-price').text('$' + currentPrice);
    $('#cart2-modal-product-quantity').text('Quantity: ' + quantity);
});

// Add to Wishlist2 Trigger: Use the Quick Look modal data for Add to Wishlist2 modal
$(document).on('click', '.add-to-wishlist2-trigger', function(e) {
    e.preventDefault();

    const name = $('#modal-product-name').text();

    // Update the Add to Wishlist2 modal with the product details
    $('#wishlist2-modal-product-name').text(name);
    $('#wishlist2-modal-product-price').text('$' + currentPrice);
});
