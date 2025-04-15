//
//     // Initialize date picker
//     let datePicker = flatpickr("#date-filter", {
//     mode: "range",
//     dateFormat: "Y-m-d",
//     onChange: function(selectedDates, dateStr) {
//     filterProducts();
// }
// });
//
//     function clearDateFilter() {
//     datePicker.clear();
//     filterProducts();
// }
//
//     // Function to filter products
//     function filterProducts() {
//     const categoryFilter = document.getElementById('category-filter').value;
//     const searchFilter = document.getElementById('search-input').value.toLowerCase();
//     const sortBy = document.getElementById('sort-by').value;
//     const dateFilter = document.getElementById('date-filter').value;
//
//     let dateRange = {start: null, end: null};
//     if (dateFilter) {
//     const dates = dateFilter.split(" to ");
//     dateRange.start = dates[0] ? new Date(dates[0]) : null;
//     dateRange.end = dates[1] ? new Date(dates[1]) : dateRange.start;
// }
//
//     const rows = document.querySelectorAll('#products-table tr');
//
//     rows.forEach(row => {
//     if (row.cells && row.cells.length > 1) { // Skip header row
//     const rowCategory = row.getAttribute('data-category');
//     const productName = row.cells[2].textContent.toLowerCase();
//
//     let showRow = true;
//
//     // Category filter
//     if (categoryFilter && rowCategory !== categoryFilter) {
//     showRow = false;
// }
//
//     // Search filter
//     if (searchFilter && !productName.includes(searchFilter)) {
//     showRow = false;
// }
//
//     row.style.display = showRow ? '' : 'none';
// }
// });
//
//     // Apply sorting
//     sortTable(sortBy);
//
//     // Update pagination after filtering
//     updatePagination();
// }
//
//     function sortTable(sortBy) {
//     const table = document.getElementById('products-table');
//     const rows = Array.from(table.rows);
//
//     rows.sort((a, b) => {
//     if (!a.cells || !b.cells) return 0;
//
//     switch(sortBy) {
//     case 'name':
//     return a.cells[2].textContent.localeCompare(b.cells[2].textContent);
//     case 'price_asc':
//     return parseFloat(a.cells[4].textContent.replace('$', '')) - parseFloat(b.cells[4].textContent.replace('$', ''));
//     case 'price_desc':
//     return parseFloat(b.cells[4].textContent.replace('$', '')) - parseFloat(a.cells[4].textContent.replace('$', ''));
//     case 'stock_asc':
//     return parseInt(a.cells[5].textContent) - parseInt(b.cells[5].textContent);
//     case 'stock_desc':
//     return parseInt(b.cells[5].textContent) - parseInt(a.cells[5].textContent);
//     default:
//     return 0;
// }
// });
//
//     // Reorder the rows
//     for (let i = 0; i < rows.length; i++) {
//     table.appendChild(rows[i]);
// }
// }
//
//     function editProduct(productId) {
//     // Redirect to edit page with product ID
//     window.location.href = 'edit_product.jsp?id=' + productId;
// }
//
//     // Initialize filters on page load
//     document.addEventListener('DOMContentLoaded', function() {
//     // Set default sort option
//     document.getElementById('sort-by').value = 'name';
//
//     // Initial filter application
//     filterProducts();
// });
