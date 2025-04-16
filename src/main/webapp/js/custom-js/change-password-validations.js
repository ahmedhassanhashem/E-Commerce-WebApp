// Wait until the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log("Password change script starting...");

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

    // Early validation to see if elements exist
    if (!currentPasswordField) console.error("Current password field not found!");
    if (!newPasswordField) console.error("New password field not found!");
    if (!confirmPasswordField) console.error("Confirm password field not found!");
    if (!passwordForm) console.error("Password form not found!");
    if (!currentPasswordStatus) console.error("Current password status not found!");
    if (!newPasswordStatus) console.error("New password status not found!");
    if (!confirmPasswordStatus) console.error("Confirm password status not found!");

    console.log("Form elements:", {
        currentPasswordField,
        newPasswordField,
        confirmPasswordField,
        passwordForm,
        currentPasswordStatus,
        newPasswordStatus,
        confirmPasswordStatus,
        formStatus
    });

    // Form validation flags
    let isCurrentPasswordValid = false;
    let isNewPasswordValid = false;
    let isConfirmPasswordValid = false;

    // IMPORTANT: Remove any existing event listeners that might be causing old messages
    // This is tricky as we can't directly remove anonymous listeners, but we can replace the elements

    // Create clones of the input fields to remove all event listeners
    if (currentPasswordField) {
        const currentPasswordClone = currentPasswordField.cloneNode(true);
        currentPasswordField.parentNode.replaceChild(currentPasswordClone, currentPasswordField);
    }

    if (newPasswordField) {
        const newPasswordClone = newPasswordField.cloneNode(true);
        newPasswordField.parentNode.replaceChild(newPasswordClone, newPasswordField);
    }

    if (confirmPasswordField) {
        const confirmPasswordClone = confirmPasswordField.cloneNode(true);
        confirmPasswordField.parentNode.replaceChild(confirmPasswordClone, confirmPasswordField);
    }

    // Re-get the fields after replacement
    const currentPasswordFieldNew = document.getElementById('current-password');
    const newPasswordFieldNew = document.getElementById('new-password');
    const confirmPasswordFieldNew = document.getElementById('confirm-new-password');

    // Clear any existing error messages
    if (currentPasswordStatus) currentPasswordStatus.textContent = "";
    if (newPasswordStatus) newPasswordStatus.textContent = "";
    if (confirmPasswordStatus) confirmPasswordStatus.textContent = "";
    if (formStatus) formStatus.textContent = "";

    // Only proceed if we found all needed elements
    if (!currentPasswordFieldNew || !newPasswordFieldNew || !confirmPasswordFieldNew ||
        !currentPasswordStatus || !newPasswordStatus || !confirmPasswordStatus) {
        console.error("Required form elements not found. Cannot initialize password validation.");
        return;
    }

    // Add event listeners for real-time validation
    currentPasswordFieldNew.addEventListener('input', validateCurrentPassword);
    newPasswordFieldNew.addEventListener('input', handleNewPasswordInput);
    confirmPasswordFieldNew.addEventListener('input', validateConfirmPassword);

    // Form events
    passwordForm.addEventListener('submit', handleFormSubmit);

    // Validate the current password
    function validateCurrentPassword() {
        const currentPassword = currentPasswordFieldNew.value.trim();

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

                        // Check if new password matches current password
                        if (newPasswordFieldNew.value) {
                            handleNewPasswordInput();
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
        return true;
    }

    // Handle input for new password with visual feedback
    function handleNewPasswordInput() {
        console.log("New password input event triggered");

        // Get the current value
        const newPassword = newPasswordFieldNew.value;

        // Clear the status area completely
        newPasswordStatus.innerHTML = '';

        // Create password strength indicator
        const strengthIndicator = document.createElement('div');
        strengthIndicator.className = 'password-strength';
        strengthIndicator.style.height = '5px';
        strengthIndicator.style.marginTop = '8px';
        strengthIndicator.style.marginBottom = '8px';
        strengthIndicator.style.transition = 'all 0.3s';
        strengthIndicator.style.backgroundColor = '#ddd';
        strengthIndicator.style.width = '100%';

        // Append the indicator to the status element
        newPasswordStatus.appendChild(strengthIndicator);

        // Check password strength components
        const hasUppercase = /[A-Z]/.test(newPassword);
        const hasLowercase = /[a-z]/.test(newPassword);
        const hasNumbers = /\d/.test(newPassword);
        const hasMinLength = newPassword.length >= 8;

        // Calculate strength
        let strength = 0;
        if (hasUppercase) strength++;
        if (hasLowercase) strength++;
        if (hasNumbers) strength++;
        if (hasMinLength) strength++;

        // Update strength indicator
        const colors = ['#ff4d4d', '#ffcc00', '#9bcd9b', '#46cc46'];
        strengthIndicator.style.backgroundColor = colors[strength - 1] || '#ddd';
        strengthIndicator.style.width = (strength * 25) + '%';

        // Create requirements list
        const requirementsList = document.createElement('div');
        requirementsList.className = 'password-requirements';
        requirementsList.style.fontSize = '12px';
        requirementsList.style.marginTop = '5px';

        const requirements = [
            { met: hasMinLength, text: 'At least 8 characters' },
            { met: hasUppercase, text: 'At least one uppercase letter' },
            { met: hasLowercase, text: 'At least one lowercase letter' },
            { met: hasNumbers, text: 'At least one number' }
        ];

        requirements.forEach(req => {
            const item = document.createElement('div');
            item.style.marginBottom = '3px';
            item.innerHTML = `${req.met ? '✓' : '○'} ${req.text}`;
            item.style.color = req.met ? '#46cc46' : '#666';
            requirementsList.appendChild(item);
        });

        newPasswordStatus.appendChild(requirementsList);

        // Set validation status
        isNewPasswordValid = (strength === 4);

        // Check if same as current password (only if current password is valid)
        if (isCurrentPasswordValid && newPassword === currentPasswordFieldNew.value.trim() && newPassword.length > 0) {
            const messageDiv = document.createElement('div');
            messageDiv.textContent = "New password must be different from current password";
            messageDiv.style.color = "#FF0000";
            messageDiv.style.marginTop = '5px';
            newPasswordStatus.appendChild(messageDiv);
            isNewPasswordValid = false;
        }

        // Revalidate confirmation if it's already entered
        if (confirmPasswordFieldNew.value) {
            validateConfirmPassword();
        }
    }

    // Validate that confirm password matches new password
    function validateConfirmPassword() {
        const newPassword = newPasswordFieldNew.value.trim();
        const confirmPassword = confirmPasswordFieldNew.value.trim();

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

    // Form submission handler
    function handleFormSubmit(event) {
        event.preventDefault(); // Prevent default form submission

        console.log("Form submission attempted");

        // Force validation of all fields
        validateCurrentPassword();
        handleNewPasswordInput();
        validateConfirmPassword();

        // Wait a moment for the async validations
        setTimeout(() => {
            // Check if all validations passed
            if (!isCurrentPasswordValid) {
                if (formStatus) {
                    formStatus.textContent = "Please enter valid current password";
                    formStatus.style.color = "#FF0000";
                }
                return;
            }

            if (!isNewPasswordValid) {
                if (formStatus) {
                    formStatus.textContent = "Please enter a valid new password";
                    formStatus.style.color = "#FF0000";
                }
                return;
            }

            if (!isConfirmPasswordValid) {
                if (formStatus) {
                    formStatus.textContent = "Please confirm your new password";
                    formStatus.style.color = "#FF0000";
                }
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

                        // Reset the form
                        passwordForm.reset();

                        // Clear all status messages
                        if (currentPasswordStatus) currentPasswordStatus.textContent = "";
                        if (newPasswordStatus) newPasswordStatus.textContent = "";
                        if (confirmPasswordStatus) confirmPasswordStatus.textContent = "";
                        if (formStatus) formStatus.textContent = "";

                        // Reset validation flags
                        isCurrentPasswordValid = false;
                        isNewPasswordValid = false;
                        isConfirmPasswordValid = false;
                    } else {
                        // Display error message from servlet
                        if (formStatus) {
                            formStatus.textContent = response.message || "Failed to update password";
                            formStatus.style.color = "#FF0000";
                        }
                    }
                } catch (e) {
                    console.error("Error parsing response:", e, xhr.responseText);
                    if (formStatus) {
                        formStatus.textContent = "Error processing server response";
                        formStatus.style.color = "#FF0000";
                    }
                }
            };

            xhr.onerror = function() {
                console.error("XHR error occurred");
                if (formStatus) {
                    formStatus.textContent = "Error connecting to server";
                    formStatus.style.color = "#FF0000";
                }
            };

            // Send form data to servlet
            const formParams = new URLSearchParams();
            formParams.append('currentPassword', currentPasswordFieldNew.value.trim());
            formParams.append('newPassword', newPasswordFieldNew.value.trim());
            formParams.append('confirmPassword', confirmPasswordFieldNew.value.trim());

            console.log("Sending request to update-password");
            xhr.send(formParams.toString());
        }, 100);
    }

    console.log("Password change script initialized");

    // Trigger input handlers for any pre-filled values
    if (newPasswordFieldNew.value) {
        handleNewPasswordInput();
    }
});