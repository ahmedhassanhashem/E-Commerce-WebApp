$(document).ready(function () {
    // Current state variables
    let currentViewMode = 'grid'; // Default to grid view
    let currentItemsPerPage = $("#showSelect").val(); // e.g., "9"
    let currentSort = $("#sortSelect").val() || ""; // Default to no sorting if empty
    let currentPage = 1; // Start at first page
    let scrollPosition = 0; // For scroll preservation

    // Helper function to gather filter parameters from the DOM
    function getFilterParams() {
        let category = $("input[name='category']:checked").val() || "";
        let priceMin = $("#price-min").val() || "";
        let priceMax = $("#price-max").val() || "";
        // If you add other filters like color and size, include them similarly.
        return {
            category: category,
            priceMin: priceMin,
            priceMax: priceMax
        };
    }

    function getInitialCategory() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('category') || '';
    }

    // Function to load products via AJAX, including filter parameters
    function loadProducts(page, itemsPerPage, sort, preserveScroll = false) {
        if (preserveScroll) {
            scrollPosition = $(window).scrollTop();
        }
        // Get current filters
        let filters = getFilterParams();



        $.ajax({
            url: 'product-list-ajax',
            type: 'GET',
            data: {
                page: page,
                show: itemsPerPage,
                sort: sort,
                category: filters.category,
                priceMin: filters.priceMin,
                priceMax: filters.priceMax
            },
            success: function (data) {
                $("#productListContainer").html(data);

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

                if (preserveScroll) {
                    $('html, body').scrollTop(scrollPosition);
                } else {
                    $('html, body').animate({
                        scrollTop: $('#productListContainer').offset().top - 120
                    }, 500);
                }
            },
            error: function (xhr, status, error) {
                console.error("Error loading products: ", error);
            }
        });
    }

    // Event handler for grid/list toggling
    $('.js-shop-grid-target').on('click', function () {
        $(this).addClass('is-active');
        $('.js-shop-list-target').removeClass('is-active');
        $('.shop-p__collection .row').addClass('is-grid-active').removeClass('is-list-active');
        currentViewMode = 'grid';
    });

    $('.js-shop-list-target').on('click', function () {
        $(this).addClass('is-active');
        $('.js-shop-grid-target').removeClass('is-active');
        $('.shop-p__collection .row').removeClass('is-grid-active').addClass('is-list-active');
        currentViewMode = 'list';
    });

    // Event handler for "Show" dropdown change
    $("#showSelect").change(function () {
        currentItemsPerPage = $(this).val();
        currentPage = 1;
        loadProducts(currentPage, currentItemsPerPage, currentSort, false);
    });

    // Event handler for "Sort By" dropdown change
    $("#sortSelect").change(function () {
        currentSort = $(this).val();
        currentPage = 1;
        loadProducts(currentPage, currentItemsPerPage, currentSort, false);
    });

    // Event handler for filter changes (category, price)
    $("input[name='category'], #price-min, #price-max").change(function () {
        currentPage = 1;
        loadProducts(currentPage, currentItemsPerPage, currentSort, false);
    });

    // Event handler for "Clear Filters" button
    $("#clearFilters").on("click", function () {
        // Reset filter inputs to default values (empty or default radio selection)
        $("input[name='category']").prop("checked", false);
        // Optionally, set one radio to default:
        // $("input[name='category'][value='']").prop("checked", true);  // if you have a "All Categories" option

        $("#price-min").val("");
        $("#price-max").val("");
        // For checkboxes (if you had color/size) use:
        // $(".color-filter, .size-filter").prop("checked", false);

        // Reload products without any filters
        currentPage = 1;
        loadProducts(currentPage, currentItemsPerPage, currentSort, false);
    });

    // Event handler for pagination clicks
    $("#productListContainer").on("click", ".pagination-link", function (e) {
        e.preventDefault();
        currentPage = $(this).data("page");
        loadProducts(currentPage, currentItemsPerPage, currentSort, true);
    });

    // Initial load of products on page load
    loadProducts(currentPage, currentItemsPerPage, currentSort, false);
});
