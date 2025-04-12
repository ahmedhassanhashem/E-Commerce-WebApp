// Get all password fields
const currentPasswordField = document.getElementById('current-password');
const newPasswordField = document.getElementById('new-password');
const confirmPasswordField = document.getElementById('confirm-new-password');
const passwordForm = document.getElementById('passwordForm');

// Get status elements
const currentPasswordStatus = document.getElementById('current-password-status');
const newPasswordStatus = document.getElementById('new-password-status');
const confirmPasswordStatus = document.getElementById('confirm-password-status');
const formStatus = document.getElementById('form-status');

// Form validation flags
let isCurrentPasswordValid = false;
let isNewPasswordValid = false;
let isConfirmPasswordValid = false;

// Add event listeners for real-time validation
currentPasswordField.addEventListener('blur', validateCurrentPassword);
newPasswordField.addEventListener('blur', validateNewPassword);
confirmPasswordField.addEventListener('blur', validateConfirmPassword);

// Optional: Validate as the user types (after a brief delay)
currentPasswordField.addEventListener('input', debounce(validateCurrentPassword, 500));
newPasswordField.addEventListener('input', debounce(validateNewPassword, 500));
confirmPasswordField.addEventListener('input', debounce(validateConfirmPassword, 500));

// Add form submission handler
passwordForm.addEventListener('submit', handleFormSubmit);

// Validate the current password
function validateCurrentPassword() {
    const currentPassword = currentPasswordField.value.trim();

    // Skip validation if field is empty
    if (!currentPassword) {
        currentPasswordStatus.textContent = "Current password is required";
        currentPasswordStatus.style.color = "#FF0000";
        isCurrentPasswordValid = false;
        return false;
    }

    // Send AJAX request to validate the password
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'validate-password', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        if (xhr.status === 200) {
            try {
                const response = JSON.parse(xhr.responseText);

                if (response.error) {
                    currentPasswordStatus.textContent = response.error;
                    currentPasswordStatus.style.color = "#FF0000";
                    isCurrentPasswordValid = false;
                } else if (response.valid) {
                    currentPasswordStatus.textContent = "Password is valid";
                    currentPasswordStatus.style.color = "#008000";
                    isCurrentPasswordValid = true;
                    // When current password is validated, also check new password
                    // (in case user entered new password first)
                    if (newPasswordField.value) {
                        validateNewPassword();
                    }
                } else {
                    currentPasswordStatus.textContent = "Invalid password";
                    currentPasswordStatus.style.color = "#FF0000";
                    isCurrentPasswordValid = false;
                }
            } catch (e) {
                console.error("Error parsing response:", e, xhr.responseText);
                currentPasswordStatus.textContent = "Error validating password";
                currentPasswordStatus.style.color = "#FF0000";
                isCurrentPasswordValid = false;
            }
        } else {
            currentPasswordStatus.textContent = "Error validating password";
            currentPasswordStatus.style.color = "#FF0000";
            isCurrentPasswordValid = false;
        }
    };

    xhr.onerror = function() {
        currentPasswordStatus.textContent = "Error connecting to server";
        currentPasswordStatus.style.color = "#FF0000";
        isCurrentPasswordValid = false;
    };

    // Send the password to the servlet
    xhr.send('currentPassword=' + encodeURIComponent(currentPassword));
    return true; // Return true to allow the validation process to continue asynchronously
}

// Validate that the new password is different from current password
function validateNewPassword() {
    const currentPassword = currentPasswordField.value.trim();
    const newPassword = newPasswordField.value.trim();

    // Reset status
    newPasswordStatus.textContent = "";

    // Check if new password field is empty
    if (!newPassword) {
        newPasswordStatus.textContent = "New password is required";
        newPasswordStatus.style.color = "#FF0000";
        isNewPasswordValid = false;
        return false;
    }

    // Check minimum length (adjust as needed)
    if (newPassword.length < 6) {
        newPasswordStatus.textContent = "Password must be at least 6 characters";
        newPasswordStatus.style.color = "#FF0000";
        isNewPasswordValid = false;
        return false;
    }

    // Check if same as current password (only if current password is valid)
    if (isCurrentPasswordValid && newPassword === currentPassword) {
        newPasswordStatus.textContent = "New password must be different from current password";
        newPasswordStatus.style.color = "#FF0000";
        isNewPasswordValid = false;
        return false;
    }

    // If we reach here, new password is valid
    newPasswordStatus.textContent = "New password is valid";
    newPasswordStatus.style.color = "#008000";
    isNewPasswordValid = true;

    // Revalidate confirmation if it's already entered
    if (confirmPasswordField.value) {
        validateConfirmPassword();
    }

    return true;
}

// Validate that confirm password matches new password
function validateConfirmPassword() {
    const newPassword = newPasswordField.value.trim();
    const confirmPassword = confirmPasswordField.value.trim();

    // Reset status
    confirmPasswordStatus.textContent = "";

    // Check if confirm password field is empty
    if (!confirmPassword) {
        confirmPasswordStatus.textContent = "Please confirm your password";
        confirmPasswordStatus.style.color = "#FF0000";
        isConfirmPasswordValid = false;
        return false;
    }

    // Check if passwords match
    if (newPassword !== confirmPassword) {
        confirmPasswordStatus.textContent = "Passwords do not match";
        confirmPasswordStatus.style.color = "#FF0000";
        isConfirmPasswordValid = false;
        return false;
    }

    // If we reach here, confirmation is valid
    confirmPasswordStatus.textContent = "Passwords match";
    confirmPasswordStatus.style.color = "#008000";
    isConfirmPasswordValid = true;
    return true;
}

// Function to reset the form and all status messages
function resetForm() {
    // Clear all input fields
    passwordForm.reset();

    // Clear all status messages
    currentPasswordStatus.textContent = "";
    newPasswordStatus.textContent = "";
    confirmPasswordStatus.textContent = "";
    formStatus.textContent = "";

    // Reset validation flags
    isCurrentPasswordValid = false;
    isNewPasswordValid = false;
    isConfirmPasswordValid = false;
}

// Check if there are any visible error messages
function hasVisibleErrors() {
    return (
        (currentPasswordStatus.textContent && currentPasswordStatus.style.color === "rgb(255, 0, 0)") ||
        (newPasswordStatus.textContent && newPasswordStatus.style.color === "rgb(255, 0, 0)") ||
        (confirmPasswordStatus.textContent && confirmPasswordStatus.style.color === "rgb(255, 0, 0)") ||
        (formStatus.textContent && formStatus.style.color === "rgb(255, 0, 0)")
    );
}

// Form submission handler
function handleFormSubmit(event) {
    event.preventDefault(); // Prevent default form submission

    // Skip if there are any visible error messages
    if (hasVisibleErrors()) {
        console.log("Form has visible errors, skipping submission");
        return;
    }

    // Validate all fields
    const isCurrentValid = validateCurrentPassword();
    const isNewValid = validateNewPassword();
    const isConfirmValid = validateConfirmPassword();

    // Check if all validations passed
    if (!isCurrentPasswordValid) {
        formStatus.textContent = "Please enter valid current password";
        formStatus.style.color = "#FF0000";
        return;
    }

    if (!isNewPasswordValid) {
        formStatus.textContent = "Please enter valid new password";
        formStatus.style.color = "#FF0000";
        return;
    }

    if (!isConfirmPasswordValid) {
        formStatus.textContent = "Please confirm your new password";
        formStatus.style.color = "#FF0000";
        return;
    }

    console.log("All validations passed, submitting form");

    // If we reach here, all validation passed, submit to server
    const xhr = new XMLHttpRequest();

    xhr.open('POST', 'update-password', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        console.log("Response received:", xhr.status, xhr.responseText);
        try {
            const response = JSON.parse(xhr.responseText);

            if (xhr.status === 200 && response.success) {
                // Show success alert
                alert(response.message);

                // Reset the form and all status messages
                resetForm();
            } else {
                // Display error message from servlet
                formStatus.textContent = response.message || "Failed to update password";
                formStatus.style.color = "#FF0000";
            }
        } catch (e) {
            console.error("Error parsing response:", e, xhr.responseText);
            formStatus.textContent = "Error processing server response";
            formStatus.style.color = "#FF0000";
        }
    };

    xhr.onerror = function() {
        console.error("XHR error occurred");
        formStatus.textContent = "Error connecting to server";
        formStatus.style.color = "#FF0000";
    };

    // Send form data to servlet
    const formParams = new URLSearchParams();
    formParams.append('currentPassword', currentPasswordField.value.trim());
    formParams.append('newPassword', newPasswordField.value.trim());
    formParams.append('confirmPassword', confirmPasswordField.value.trim());

    console.log("Sending request to update-password");
    xhr.send(formParams.toString());
}

// Utility function to debounce input events
function debounce(func, delay) {
    let timeout;
    return function() {
        const context = this;
        const args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(function() {
            func.apply(context, args);
        }, delay);
    };
}

// Initialize the script
document.addEventListener('DOMContentLoaded', function() {
    console.log("Password change script initialized");
});