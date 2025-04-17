// Form submission handler
$(document).ready(function() {
    $('#add-product-form').on('submit', function (e) {
        e.preventDefault();

        // Get form data
        const formData = new FormData(this);

        // AJAX submission
        $.ajax({
            url: 'AddProduct',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            dataType: 'json',
            success: function (response) {
                showNotification(response.message, response.status === 'success' ? 'success' : 'error');

                if (response.status === 'success') {
                    setTimeout(function () {
                        window.location.href = 'admin-dashboard.jsp';
                    }, 1500);
                }
            },
            error: function (xhr, status, error) {
                showNotification('Error adding product: ' + error, 'error');
            }
        });
    });
});
    function previewImage(input) {
        const fileDisplay = document.getElementById('file-name-display');
        const previewImg = document.getElementById('preview-img');
        const previewText = document.querySelector('.image-preview-text');

        if (input.files && input.files[0]) {
            fileDisplay.textContent = input.files[0].name;

            const reader = new FileReader();

            reader.onload = function(e) {
                previewImg.src = e.target.result;
                previewImg.style.display = 'block';
                previewText.style.display = 'none';
            }
            reader.readAsDataURL(input.files[0]);
        } else {
            fileDisplay.textContent = 'No file chosen';
            previewImg.style.display = 'none';
            previewText.style.display = 'block';
        }
    }
    // Show notification
    function showNotification(message, type) {
        const notification = $('#notification');
        notification.removeClass('notification-success notification-error');
        notification.addClass(`notification-${type}`);
        notification.text(message);
        notification.css('display', 'block');

        // Hide notification after 3 seconds
        setTimeout(() => {
        notification.css('display', 'none');
    }, 3000);
}