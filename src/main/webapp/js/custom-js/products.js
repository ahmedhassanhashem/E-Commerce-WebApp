$(document).ready(function () {
    // State management object
    const productState = {
        currentPage: 1,
        itemsPerPage: 9,
        sortBy: '',
        viewMode: 'grid',
        filters: {
            category: '',
            priceMin: '',
            priceMax: '',
            search: ''
        }
    };

    // DOM Elements cache
    const domElements = {
        productContainer: $('#productListContainer'),
        showSelect: $('#showSelect'),
        sortSelect: $('#sortSelect'),
        priceMin: $('#price-min'),
        priceMax: $('#price-max'),
        categoryRadios: $("input[name='category']"),
        clearFilters: $('#clearFilters')
    };

    // Initialize filters from URL
    function initFiltersFromURL() {
        const urlParams = new URLSearchParams(window.location.search);
        productState.filters = {
            category: urlParams.get('category') || '',
            priceMin: urlParams.get('priceMin') || '',
            priceMax: urlParams.get('priceMax') || '',
            search: urlParams.get('search') || ''
        };
    }

    // Unified AJAX call handler
    function loadProducts(preserveScroll = false) {
        const params = new URLSearchParams({
            action: 'filter',
            page: productState.currentPage,
            show: productState.itemsPerPage,
            sort: productState.sortBy,
            ...productState.filters
        });

        if (preserveScroll) {
            productState.scrollPosition = $(window).scrollTop();
        }

        $.ajax({
            url: 'product-list',
            type: 'GET',
            data: params.toString(),
            success: function(data) {
                domElements.productContainer.html(data);
                updateViewMode();
                restoreScrollPosition(preserveScroll);
                updateActivePagination();
            },
            error: handleAjaxError
        });
    }

    // View mode management
    function updateViewMode() {
        const rowElement = $('.shop-p__collection .row');
        const isGrid = productState.viewMode === 'grid';

        rowElement.toggleClass('is-grid-active', isGrid)
            .toggleClass('is-list-active', !isGrid);

        $('.js-shop-grid-target, .js-shop-list-target')
            .removeClass('is-active')
            .filter(`.js-shop-${productState.viewMode}-target`)
            .addClass('is-active');
    }

    // Event handlers
    function bindEvents() {
        // View mode toggles
        $('.js-shop-grid-target, .js-shop-list-target').on('click', function() {
            productState.viewMode = $(this).hasClass('js-shop-grid-target') ? 'grid' : 'list';
            updateViewMode();
        });

        // Filter controls
        domElements.showSelect.on('change', function() {
            productState.itemsPerPage = $(this).val();
            productState.currentPage = 1;
            loadProducts();
        });

        domElements.sortSelect.on('change', function() {
            productState.sortBy = $(this).val();
            productState.currentPage = 1;
            loadProducts();
        });

        // Filter changes
        domElements.categoryRadios.add(domElements.priceMin).add(domElements.priceMax).on('change', function() {
            productState.currentPage = 1;
            productState.filters = {
                category: $("input[name='category']:checked").val() || '',
                priceMin: domElements.priceMin.val(),
                priceMax: domElements.priceMax.val(),
                search: productState.filters.search
            };
            loadProducts();
        });

        // Clear filters
        domElements.clearFilters.on('click', function() {
            productState.filters = { category: '', priceMin: '', priceMax: '', search: '' };
            productState.currentPage = 1;
            domElements.categoryRadios.prop('checked', false);
            domElements.priceMin.val('');
            domElements.priceMax.val('');
            loadProducts();
        });

        // Pagination
        domElements.productContainer.on('click', '.pagination-link', function(e) {
            e.preventDefault();
            productState.currentPage = $(this).data('page');
            loadProducts(true);
        });
    }

    // Helper functions
    function restoreScrollPosition(preserve) {
        if (preserve) {
            $('html, body').scrollTop(productState.scrollPosition);
        } else {
            $('html, body').animate({
                scrollTop: domElements.productContainer.offset().top - 120
            }, 500);
        }
    }

    function updateActivePagination() {
        $(`.pagination-link[data-page="${productState.currentPage}"]`)
            .addClass('is-active')
            .siblings().removeClass('is-active');
    }

    function handleAjaxError(xhr, status, error) {
        console.error("Product loading error:", error);
        domElements.productContainer.html('<div class="alert alert-danger">Error loading products. Please try again.</div>');
    }

    // Initialization
    initFiltersFromURL();
    bindEvents();
    loadProducts();
});