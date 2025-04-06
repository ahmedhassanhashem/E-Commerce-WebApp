$(document).on('click', '.quick-look-trigger', function(e) {
    e.preventDefault();

    // Retrieve data from the clicked element
    const image = $(this).data('image');
    const name = $(this).data('name');
    const description = $(this).data('description');
    const stock = $(this).data('stock');

    // Update the modal with the retrieved data
    $('#modal-product-image').attr('src', 'images/product/electronic/' + image + '.jpg');
    $('#modal-product-name').text(name);
    $('#modal-product-description').text(description);

    // Update stock status and counter max attribute
    if (stock > 0) {
        $('#modal-product-stock').text("In Stock")
            .removeClass('pd-detail__left')
            .addClass('pd-detail__stock');
    } else {
        $('#modal-product-stock').text("Out of Stock")
            .removeClass('pd-detail__stock')
            .addClass('pd-detail__left');
    }
    $('#modal-product-stock-input').attr('data-max', stock);
});
