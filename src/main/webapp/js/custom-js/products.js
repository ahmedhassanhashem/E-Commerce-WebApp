$(document).ready(function() {
    // Set default values and initialize view state
    let currentViewMode = 'grid'; // Default to grid view
    let currentItemsPerPage = "9"; // Default to 9 items
    let currentSort = ""; // Default to no sorting
    let scrollPosition = 0; // Variable to store scroll position

    // Set initial active states for show and sort dropdowns
    $("#showSelect").val(currentItemsPerPage);
    $("#sortSelect").val(currentSort);

    // Load initial products on page load
    loadProducts(1, currentItemsPerPage, currentSort);

    // Toggle Grid/List View
    $('.js-shop-grid-target').on('click', function() {
        // Set grid view active
        $(this).addClass('is-active');
        $('.js-shop-list-target').removeClass('is-active');
        // Add grid class to row
        $('.shop-p__collection .row').addClass('is-grid-active').removeClass('is-list-active');
        currentViewMode = 'grid';
    });

    $('.js-shop-list-target').on('click', function() {
        // Set list view active
        $(this).addClass('is-active');
        $('.js-shop-grid-target').removeClass('is-active');
        // Add list class to row
        $('.shop-p__collection .row').removeClass('is-grid-active').addClass('is-list-active');
        currentViewMode = 'list';
    });

    // Function to load products via AJAX
    function loadProducts(page, itemsPerPage, sort, preserveScroll = false) {
        // Save scroll position before making the AJAX call if preserveScroll is true
        if (preserveScroll) {
            scrollPosition = $(window).scrollTop();
        }

        $.ajax({
            url: 'product-list-ajax',
            type: 'GET',
            data: {
                page: page,
                show: itemsPerPage,
                sort: sort
            },
            success: function(data) {
                $("#productListContainer").html(data);

                // DO NOT call initializeModals() here - this is what's opening all modals

                // Re-apply current view mode after content is loaded
                if (currentViewMode === 'grid') {
                    $('.js-shop-grid-target').addClass('is-active');
                    $('.js-shop-list-target').removeClass('is-active');
                    $('.shop-p__collection .row').addClass('is-grid-active').removeClass('is-list-active');
                } else {
                    $('.js-shop-list-target').addClass('is-active');
                    $('.js-shop-grid-target').removeClass('is-active');
                    $('.shop-p__collection .row').removeClass('is-grid-active').addClass('is-list-active');
                }

                // Restore scroll position if preserveScroll is true
                if (preserveScroll) {
                    $('html, body').scrollTop(scrollPosition);
                } else {
                    // Scroll to top of product list for better UX when changing items per page or sort
                    $('html, body').animate({
                        scrollTop: $('#productListContainer').offset().top - 120 // Offset for header
                    }, 500);
                }
            },
            error: function(xhr, status, error) {
                console.error("Error loading products: ", error);
            }
        });
    }

    // Handle show per page changes
    $("#showSelect").change(function(){
        currentItemsPerPage = $(this).val();
        loadProducts(1, currentItemsPerPage, currentSort, false); // Reset to page 1, don't preserve scroll
    });

    // Handle sort changes
    $("#sortSelect").change(function(){
        currentSort = $(this).val();
        loadProducts(1, currentItemsPerPage, currentSort, false); // Reset to page 1, don't preserve scroll
    });

    // Handle pagination clicks using event delegation
    $(document).on("click", ".pagination-link", function(e){
        e.preventDefault();
        const page = $(this).data("page");
        loadProducts(page, currentItemsPerPage, currentSort, true); // Preserve scroll position for pagination
    });


});


