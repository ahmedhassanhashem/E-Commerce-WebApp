document.addEventListener('DOMContentLoaded', function () {
    const cancelOrderBtn = document.getElementById('cancelOrderBtn');
    if (cancelOrderBtn) {
        cancelOrderBtn.addEventListener('click', function () {
            const form = document.getElementById('cancelOrderForm');
            const formData = new FormData(form);

            fetch('updateOrderStatus', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
                .then(response => response.json())
                .then(data => {
                    const statusMessage = document.getElementById('statusMessage');

                    if (data.success) {
                        // Change the status display to CANCELLED
                        const statusElement = document.querySelector('.manage-o__text.u-c-brand');
                        if (statusElement) {
                            statusElement.textContent = 'CANCELLED';
                        }

                        // Remove the cancel order button div
                        const orderActionsDiv = document.getElementById('cancelOrderForm').closest('.dash__box');
                        if (orderActionsDiv) {
                            orderActionsDiv.remove();
                        }

                        // Update orders statistics by refreshing the sidebar component
                        updateOrdersStatistics();

                    } else {
                        statusMessage.innerHTML = '<div class="alert alert-danger">' + data.message + '</div>';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    const statusMessage = document.getElementById('statusMessage');
                    if (statusMessage) {
                        statusMessage.innerHTML = '<div class="alert alert-danger">An error occurred while processing your request.</div>';
                    }
                });
        });
    }

    // Function to update the orders statistics in the sidebar
    function updateOrdersStatistics() {
        fetch(contextPath + '/commos/dashboard-sidebar-orders-statistics.jsp')
            .then(response => response.text())
            .then(html => {
                // Find the orders statistics container and replace its content
                const statisticsContainer = document.querySelector('.col-lg-3.col-md-12 .dash__box.dash__box--bg-white.dash__box--shadow.dash__box--w');
                if (statisticsContainer) {
                    // Create a temporary container to parse the response HTML
                    const tempDiv = document.createElement('div');
                    tempDiv.innerHTML = html;

                    // Find the new statistics box in the parsed HTML
                    const newStatisticsBox = tempDiv.querySelector('.dash__box.dash__box--bg-white.dash__box--shadow.dash__box--w');
                    if (newStatisticsBox) {
                        // Replace the old statistics box with the new one
                        statisticsContainer.replaceWith(newStatisticsBox);
                    }
                }
            })
            .catch(error => {
                console.error('Error updating orders statistics:', error);
            });
    }
});