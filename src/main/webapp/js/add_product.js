
//     // Preview image when selected
//     function previewImage(input) {
//     const preview = document.getElementById('preview-img');
//     const previewText = document.querySelector('.image-preview-text');
//     const fileNameDisplay = document.getElementById('file-name-display');
//
//     if (input.files && input.files[0]) {
//     const fileName = input.files[0].name;
//     fileNameDisplay.textContent = fileName;
//
//     const reader = new FileReader();
//     reader.onload = function(e) {
//     preview.src = e.target.result;
//     preview.style.display = 'block';
//     previewText.style.display = 'none';
// }
//     reader.readAsDataURL(input.files[0]);
// } else {
//     preview.style.display = 'none';
//     previewText.style.display = 'block';
//     fileNameDisplay.textContent = 'No file chosen';
// }
// }

    // Form submission handler
    $(document).ready(function() {
    $('#add-product-form').on('submit', function(e) {
        e.preventDefault();

        // Get form data
        const formData = new FormData(this);

        // AJAX submission
        $.ajax({
            url: 'ProductServlet',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                showNotification('Product added successfully!', 'success');

                // Reset form after 1 second and redirect
                setTimeout(function() {
                    window.location.href = 'edit-products.jsp';
                }, 1500);
            },
            error: function(xhr, status, error) {
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
