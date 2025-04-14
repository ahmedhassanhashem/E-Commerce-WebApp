function validatePassword(password) {
    // Password must be at least 8 characters with at least one uppercase, one lowercase and one number
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    return passwordRegex.test(password);
}

function validateResetForm() {
    const newPassword = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const errorDiv = document.getElementById('password-error');
    const requirementsDiv = document.getElementById('password-requirements');
    
    // Check password strength
    if (!validatePassword(newPassword)) {
        requirementsDiv.style.color = 'red';
        return false;
    }
    
    // Check if passwords match
    if (newPassword !== confirmPassword) {
        errorDiv.style.display = 'block';
        return false;
    } else {
        errorDiv.style.display = 'none';
        return true;
    }
}

// Password strength meter
document.getElementById('new-password').addEventListener('input', function() {
    const password = this.value;
    const strengthIndicator = document.getElementById('password-strength');
    const requirementsDiv = document.getElementById('password-requirements');
    
    // Check password strength components
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
    
    // Update requirements text color
    if (validatePassword(password)) {
        requirementsDiv.style.color = '#46cc46';
    } else {
        requirementsDiv.style.color = '#666';
    }
});

// Real-time validation for confirm password
document.getElementById('confirm-password').addEventListener('input', function() {
    const newPassword = document.getElementById('new-password').value;
    const confirmPassword = this.value;
    const errorDiv = document.getElementById('password-error');
    
    if (newPassword !== confirmPassword) {
        errorDiv.style.display = 'block';
    } else {
        errorDiv.style.display = 'none';
    }
});
///////////////////////////////////////////
function validateForgotPassEmail(email) {
    // Simple email validation regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function validatePhone(phone) {
    // Phone validation regex (accepts formats like +1234567890, 123-456-7890, (123) 456-7890, etc.)
    const phoneRegex = /^(\+\d{1,3}[- ]?)?\d{10,14}$/;
    return phoneRegex.test(phone.replace(/[\s()\-]/g, ''));
}

function validateForm() {
    const email = document.getElementById('reset-email').value.trim();
    const phone = document.getElementById('reset-cc').value.trim();
    const emailError = document.getElementById('email-error');
    const phoneError = document.getElementById('phone-error');
    
    let isValid = true;
    
    // Validate email
    if (!validateEmail(email)) {
        emailError.style.display = 'block';
        isValid = false;
    } else {
        emailError.style.display = 'none';
    }
    
    // Validate phone
    if (!validatePhone(phone)) {
        phoneError.style.display = 'block';
        isValid = false;
    } else {
        phoneError.style.display = 'none';
    }
    
    return isValid;
}

// Real-time validation for email
document.getElementById('reset-email').addEventListener('blur', function() {
    const email = this.value.trim();
    const emailError = document.getElementById('email-error');
    
    if (!validateEmail(email) && email !== '') {
        emailError.style.display = 'block';
    } else {
        emailError.style.display = 'none';
    }
});

// Real-time validation for phone
document.getElementById('reset-cc').addEventListener('blur', function() {
    const phone = this.value.trim();
    const phoneError = document.getElementById('phone-error');
    
    if (!validatePhone(phone) && phone !== '') {
        phoneError.style.display = 'block';
    } else {
        phoneError.style.display = 'none';
    }
});
