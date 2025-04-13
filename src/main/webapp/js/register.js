document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.l-f-o__form');
    
    if (form) {
        form.addEventListener('submit', function(event) {

            event.preventDefault();
            
            clearErrorMessages();
            
            const name = document.getElementById('reg-name').value.trim();
            const email = document.getElementById('reg-email').value.trim();
            const phone = document.getElementById('reg-phone').value.trim();
            const address = document.getElementById('reg-address').value.trim();
            const credit = document.getElementById('reg-credit').value.trim();
            const password = document.getElementById('reg-password').value;
            const confirmPassword = document.getElementById('reg-confirm-password').value;
            
            // Validation flags
            let isValid = true;
            
            // Validate name (not empty and at least 2 characters)
            if (name.length < 2) {
                displayError('reg-name', 'Name must be at least 2 characters long');
                isValid = false;
            }
            
            // Validate email
            if (!validateEmail(email)) {
                displayError('reg-email', 'Please enter a valid email address');
                isValid = false;
            }
            
            // Validate phone (numeric with optional country code)
            if (!validatePhone(phone)) {
                displayError('reg-phone', 'Please enter a valid phone number');
                isValid = false;
            }
            
            // Validate address (not empty)
            if (address.length < 5) {
                displayError('reg-address', 'Please enter a valid address (at least 5 characters)');
                isValid = false;
            }
            
            // Validate credit (positive number)
            if (isNaN(credit) || parseFloat(credit) < 0) {
                displayError('reg-credit', 'Credit must be a positive number');
                isValid = false;
            }
            
            // Validate password strength
            if (!validatePassword(password)) {
                displayError('reg-password', 'Password must be at least 8 characters with at least one uppercase letter, one lowercase letter, and one number');
                isValid = false;
            }
            
            // Validate password confirmation
            if (password !== confirmPassword) {
                displayError('reg-confirm-password', 'Passwords do not match');
                isValid = false;
            }
            
            // If all validations pass, submit the form
            if (isValid) {
                form.submit();
            }
        });
    }
});

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function validatePhone(phone) {
    // Phone validation regex (accepts formats like +1234567890, 123-456-7890, (123) 456-7890, etc.)
    const phoneRegex = /^(\+\d{1,3}[- ]?)?\d{10,14}$/;
    return phoneRegex.test(phone.replace(/[\s()\-]/g, ''));
}

function validatePassword(password) {
    // Password must be at least 8 characters with at least one uppercase, one lowercase and one number
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    return passwordRegex.test(password);
}

function displayError(inputId, message) {
    const input = document.getElementById(inputId);
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.style.color = 'red';
    errorDiv.style.fontSize = '12px';
    errorDiv.style.marginTop = '5px';
    errorDiv.textContent = message;
    
    input.parentNode.insertBefore(errorDiv, input.nextSibling);
    
    input.style.borderColor = 'red';
}

function clearErrorMessages() {

    const errorMessages = document.querySelectorAll('.error-message');
    errorMessages.forEach(message => message.remove());
    
    const inputs = document.querySelectorAll('.input-text');
    inputs.forEach(input => input.style.borderColor = '');
}

// Add real-time validation for email field
document.getElementById('reg-email').addEventListener('blur', function() {
    const email = this.value.trim();
    clearFieldError(this);
    
    if (!validateEmail(email) && email !== '') {
        displayError('reg-email', 'Please enter a valid email address');
    }
});

// Add real-time validation for phone field
document.getElementById('reg-phone').addEventListener('blur', function() {
    const phone = this.value.trim();
    clearFieldError(this);
    
    if (!validatePhone(phone) && phone !== '') {
        displayError('reg-phone', 'Please enter a valid phone number');
    }
});

// Add real-time validation for password field
document.getElementById('reg-password').addEventListener('input', function() {
    const password = this.value;
    clearFieldError(this);
    
    // Create or update password strength indicator
    let strengthIndicator = this.nextElementSibling;
    if (!strengthIndicator || !strengthIndicator.classList.contains('password-strength')) {
        strengthIndicator = document.createElement('div');
        strengthIndicator.className = 'password-strength';
        strengthIndicator.style.height = '5px';
        strengthIndicator.style.marginTop = '5px';
        strengthIndicator.style.transition = 'all 0.3s';
        this.parentNode.insertBefore(strengthIndicator, this.nextSibling);
    }
    
    // Check password strength
    const hasUppercase = /[A-Z]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasNumbers = /\d/.test(password);
    const hasMinLength = password.length >= 8;
    
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
});

function clearFieldError(inputElement) {
    const nextSibling = inputElement.nextElementSibling;
    if (nextSibling && nextSibling.classList.contains('error-message')) {
        nextSibling.remove();
    }
    
    inputElement.style.borderColor = '';
}

// Add real-time validation for confirm password
document.getElementById('reg-confirm-password').addEventListener('input', function() {
    const confirmPassword = this.value;
    const password = document.getElementById('reg-password').value;
    clearFieldError(this);
    
    if (confirmPassword !== password && confirmPassword !== '') {
        displayError('reg-confirm-password', 'Passwords do not match');
    }
});