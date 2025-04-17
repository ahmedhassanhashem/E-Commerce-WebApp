$(document).ready(function() {
    // Function to refresh product sections on home page
    function refreshHomeProducts() {
        // Only run if we're on the home page (checking for product tab sections)
        if ($('#electronic-01, #female-02, #male-03').length > 0) {
            $.ajax({
                url: 'home',
                type: 'GET',
                data: {
                    action: 'refresh',
                    timestamp: new Date().getTime() // Prevent caching
                },
                success: function(data) {
                    // Extract the product sections from the returned HTML
                    const tempDiv = $('<div></div>').html(data);

                    // Update Beans section
                    const beansTab = tempDiv.find('#item-bean .owl-carousel').html();
                    $('#item-bean .owl-carousel').html(beansTab);

                    // Update Mugs section
                    const mugsTab = tempDiv.find('#item-mug .owl-carousel').html();
                    $('#item-mug .owl-carousel').html(mugsTab);

                    // Update Machines section
                    const machinesTab = tempDiv.find('#item-machine .owl-carousel').html();
                    $('#item-machine .owl-carousel').html(machinesTab);

                    // Reinitialize any carousels or event handlers
                    reinitializeComponents();

                    // Show a subtle notification
                    showUpdateNotification();
                },
                error: function(xhr, status, error) {
                    console.error("Error refreshing products:", error);
                }
            });
        }
    }

    // Reinitialize components after updating content
    function reinitializeComponents() {
        // Reinitialize sliders/carousels if needed
        if ($.fn.owlCarousel) {
            $('.tab-slider').each(function() {
                const $this = $(this);
                // Destroy existing carousel and reinitialize
                if ($this.data('owl.carousel')) {
                    $this.trigger('destroy.owl.carousel');
                }
                $this.owlCarousel({
                    items: 4,
                    loop: false,
                    margin: 10,
                    autoplay: false,
                    responsive: {
                        0: {items: 1},
                        768: {items: 2},
                        992: {items: 3},
                        1200: {items: 4}
                    }
                });
            });
        }

        // Reattach modal triggers
        attachModalTriggers();
    }

    // Reattach modal trigger events after content update
    function attachModalTriggers() {
        // Quick look triggers
        $('.quick-look-trigger').on('click', function() {
            const modalId = $(this).data('modal-id');
            const productName = $(this).data('name');
            const productImage = $(this).data('image');
            const productPrice = $(this).data('price');
            const productDescription = $(this).data('description');
            const productStock = $(this).data('stock');
            const productId = $(this).data('id');

            // Update modal content with product info
            $(modalId).find('.modal-product-name').text(productName);
            $(modalId).find('.modal-product-image').attr('src', `images/product/electronic/${productImage}.jpg`);
            $(modalId).find('.modal-product-price').text('$' + productPrice);
            $(modalId).find('.modal-product-description').text(productDescription);
            $(modalId).find('.modal-product-stock').text(productStock);
            $(modalId).find('.modal-product-id').val(productId);
        });

        // Add to cart triggers
        $('.add-to-cart-trigger').on('click', function() {
            const modalId = $(this).data('modal-id');
            const productName = $(this).data('name');
            const productImage = $(this).data('image');
            const productPrice = $(this).data('price');
            const productStock = $(this).data('stock');
            const productId = $(this).data('id');

            // Update modal content
            $(modalId).find('.modal-product-name').text(productName);
            $(modalId).find('.modal-product-image').attr('src', `images/product/electronic/${productImage}.jpg`);
            $(modalId).find('.modal-product-price').text('$' + productPrice);
            $(modalId).find('.modal-product-stock').text(productStock);
            $(modalId).find('.modal-product-id').val(productId);
        });
    }

    // Show update notification
    function showUpdateNotification() {
        const notification = $('<div class="update-notification">Products updated</div>');
        notification.css({
            'position': 'fixed',
            'bottom': '20px',
            'right': '20px',
            'background-color': '#4CAF50',
            'color': 'white',
            'padding': '10px 20px',
            'border-radius': '4px',
            'box-shadow': '0 2px 5px rgba(0,0,0,0.2)',
            'z-index': '9999',
            'opacity': '0',
            'transition': 'opacity 0.3s ease'
        });

        $('body').append(notification);

        // Fade in and out
        setTimeout(() => {
            notification.css('opacity', '1');
            setTimeout(() => {
                notification.css('opacity', '0');
                setTimeout(() => {
                    notification.remove();
                }, 300);
            }, 2000);
        }, 100);
    }

    // Set interval for auto-refresh (30 seconds)
    setInterval(refreshHomeProducts, 30000);
});