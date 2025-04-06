$(document).on('click', '.quick-look-trigger', function(e) {
    e.preventDefault();
    var image = $(this).data('image');
    var name = $(this).data('name');
    var description = $(this).data('description');
    var stock = $(this).data('stock');

    // Update modal content
    $('#modal-product-image').attr('src', 'images/product/' + image + '.jpg');
    $('#modal-product-name').text(name);
    $('#modal-product-description').text(description);

    // Update stock status
    if (stock > 0) {
        $('#modal-product-stock').text("In Stock").removeClass('pd-detail__left').addClass('pd-detail__stock');
    } else {
        $('#modal-product-stock').text("Out of Stock").removeClass('pd-detail__stock').addClass('pd-detail__left');
    }

    // Update the max attribute for the counter
    $('#modal-product-stock-input').attr('data-max', stock);
});
