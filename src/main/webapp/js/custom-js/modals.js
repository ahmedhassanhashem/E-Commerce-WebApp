// Declare this at the top of your file
let currentPrice = 0;

// Use event delegation for modal triggers and manually handle modal opening
$(document).on('click', '.quick-look-trigger', function(e) {
    e.preventDefault();

    // Get modal ID
    const modalId = $(this).data('modal-id');

    // Retrieve data from the clicked element
    const image = $(this).data('image');
    const name = $(this).data('name');
    const description = $(this).data('description');
    const stock = $(this).data('stock');
    const price = $(this).data('price');
    const id = $(this).data('id');

    // Store the price in the global variable for later use
    currentPrice = price;

    // Update the Quick Look modal with the retrieved data
    $('#modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#modal-product-name').text(name);
    $('#modal-product-description').text(description);
    $('#modal-product-id').text(id);

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
    $('#quick-look').find('.add-to-cart2-trigger')
  .attr('data-product-id', id)
  .attr('data-id', id)
  .attr('data-image',    image)
  .attr('data-name',     name)
  .attr('data-price',    price);


    // Manually open the modal (jQuery method)
    $(modalId).modal('show');
});

$(document).on('click', '.add-to-cart-trigger', function(e) {
    e.preventDefault();

    const modalId = $(this).data('modal-id');
    const image = $(this).data('image');
    const name = $(this).data('name');
    const price = $(this).data('price');
    const id = $(this).data('product-id');

    $('#cart-modal-product-id').text(id);
    $('#cart-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#cart-modal-product-name').text(name);
    $('#cart-modal-product-price').text('$' + price);
    $('#quick-look').find('.add-to-cart-trigger')
        .attr('data-product-id', id)
        .attr('data-image', image)
        .attr('data-name', name)
        .attr('data-price', price);

    // Manually open the modal
    $(modalId).modal('show');
});

// // Similarly for the other modal triggers
// $(document).on('click', '.add-to-wishlist-trigger', function(e) {
//     e.preventDefault();
//
//     const modalId = $(this).data('modal-id');
//     const image = $(this).data('image');
//     const name = $(this).data('name');
//     const price = $(this).data('price');
//     const id = $(this).data('product-id');
//
//     $('#wishlist-modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
//     $('#wishlist-modal-product-name').text(name);
//     $('#wishlist-modal-product-price').text('$' + price);
//
//     // Manually open the modal
//     $(modalId).modal('show');
// });
//
// $(document).on('click', '.add-to-cart2-trigger', function(e) {
//     e.preventDefault();
//
//     const modalId = $(this).data('modal-id');
//     const quantity = $('#modal-product-stock-input').val();
//     const name = $('#modal-product-name').text();
//     const id = $(this).data('product-id');
//
//     $('#cart2-modal-product-name').text(name);
//     $('#cart2-modal-product-price').text('$' + currentPrice);
//     $('#cart2-modal-product-quantity').text('Quantity: ' + quantity);
//
//     // Manually open the modal
//     $(modalId).modal('show');
// });

// $(document).on('click', '.add-to-wishlist2-trigger', function(e) {
//     e.preventDefault();
//
//     const modalId = $(this).data('modal-id');
//     const name = $('#modal-product-name').text();
//     const id = $(this).data('product-id');
//
//     $('#wishlist2-modal-product-name').text(name);
//     $('#wishlist2-modal-product-price').text('$' + currentPrice);
//
//     // Manually open the modal
//     $(modalId).modal('show');
// });