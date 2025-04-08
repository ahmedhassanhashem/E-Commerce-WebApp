document.addEventListener('DOMContentLoaded', function () {
    const patterns = {
        name: /^[A-Za-z ]{2,50}$/,
        email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/,
        phone: /^01[0125]\d{8}$/,
        address: /^.{5,100}$/
    };


    const form = document.getElementById('profileForm');
    const nameInput = document.getElementById('reg-name');
    const emailInput = document.getElementById('reg-email');
    const phoneInput = document.getElementById('reg-phone');
    const addressInput = document.getElementById('reg-address');


    nameInput?.addEventListener('input', () => validateField(nameInput, patterns.name, 'name-error', '❌ Only letters and spaces (2-50 characters)'));
    emailInput?.addEventListener('input', validateEmailAjax);
    phoneInput?.addEventListener('input', () => validateField(phoneInput, patterns.phone, 'phone-error', '❌ Must start with 010, 011, 012, or 015 followed by 8 digits'));
    addressInput?.addEventListener('input', () => validateField(addressInput, patterns.address, 'address-error', '❌ Address must be 5 to 100 characters')
    );

    let emailValid = true;

    function validateEmailAjax() {
        const email = emailInput.value.trim();
        const errorElement = document.getElementById('email-error');

        if (!patterns.email.test(email)) {
            errorElement.textContent = "❌ Invalid email format (e.g., user@example.com)";
            errorElement.style.color = "red";
            emailValid = false;
            return;
        }

        // Call AJAX to check email existence
        fetch(`check-email?email=${encodeURIComponent(email)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    errorElement.textContent = "❌ Email already in use!";
                    errorElement.style.color = "red";
                    emailValid = false;
                } else {
                    errorElement.textContent = "✅ Email available!";
                    errorElement.style.color = "green";
                    emailValid = true;
                }
            })
            .catch(error => {
                console.error("Error checking email:", error);
                emailValid = false;
            });
    }

    // Generic validation function
    function validateField(field, pattern, errorId, message) {
        const value = field.value.trim();
        const errorElement = document.getElementById(errorId);
        const isValid = pattern.test(value);

        if (!isValid) {
            errorElement.textContent = message;
            errorElement.style.color = "red";
            field.classList.add('input-error');
        } else {
            errorElement.textContent = "";
            field.classList.remove('input-error');
        }

        return isValid;
    }

    // Form submission handler
    form?.addEventListener('submit', function (e) {
        e.preventDefault();
        let isValid = true;

        // Validate all fields
        isValid = validateField(nameInput, patterns.name, 'name-error', '❌ Only letters and spaces (2-50 characters)') && isValid;
        isValid = validateField(phoneInput, patterns.phone, 'phone-error', '❌ Must start with 010, 011, 012, or 015 followed by 8 digits') && isValid;
        isValid = validateField(addressInput, patterns.address, 'address-error', '❌ Address must be 5 to 100 characters') && isValid;


        if (!emailValid) {
            alert("Please enter a valid email that is not already in use.");
            emailInput.focus();
            return;
        }

        if (!isValid) return;


        const formData = new FormData(form);


        // Send AJAX request to update profile
        fetch('update-profile', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Profile updated successfully!');
                    // location.reload();
                } else {
                    alert('Error updating profile: ' + data.message);
                }
            })
            .catch(error => console.error("Error updating profile:", error));
    });
});