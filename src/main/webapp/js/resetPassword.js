// Password validation script
document.addEventListener('DOMContentLoaded', function() {
    // Function to display error messages
    function displayError(fieldId, message) {
        const field = document.getElementById(fieldId);
        if (field) {
            let errorElement = field.nextElementSibling;
            if (!errorElement || !errorElement.classList.contains('error-message')) {
                errorElement = document.createElement('div');
                errorElement.className = 'error-message';
                errorElement.style.color = 'red';
                errorElement.style.fontSize = '12px';
                errorElement.style.marginTop = '5px';
                field.parentNode.insertBefore(errorElement, field.nextSibling);
            }
            errorElement.textContent = message;
            errorElement.style.display = 'block';
        }
    }

    // Function to clear error messages
    function clearFieldError(field) {
        const errorElement = field.nextElementSibling;
        if (errorElement && errorElement.classList.contains('error-message')) {
            errorElement.style.display = 'none';
        }
    }

    // Add real-time validation for password field
    const passwordField = document.getElementById('password');
    const passwordRequirements = document.getElementById('password-requirements');
    const passwordStrength = document.getElementById('password-strength');
    
    if (passwordField) {
        passwordField.addEventListener('input', function() {
            const password = this.value;
            
            // Check password strength
            const hasUppercase = /[A-Z]/.test(password);
            const hasLowercase = /[a-z]/.test(password);
            const hasNumbers = /\d]/.test(password);
            const hasMinLength = password.length >= 8;
            
            // Calculate strength
            let strength = 0;
            if (hasUppercase) strength++;
            if (hasLowercase) strength++;
            if (hasNumbers) strength++;
            if (hasMinLength) strength++;
            
            // Update strength indicator
            const colors = ['#ff4d4d', '#ffcc00', '#9bcd9b', '#46cc46'];
            passwordStrength.style.backgroundColor = colors[strength - 1] || '#ddd';
            passwordStrength.style.width = (strength * 25) + '%';
            
            // Update requirements text
            if (password.length === 0) {
                passwordRequirements.textContent = 'Password must contain at least 8 characters with uppercase, lowercase, and numbers';
                passwordRequirements.style.color = '#666';
            } else if (strength < 4) {
                let missing = [];
                if (!hasMinLength) missing.push('at least 8 characters');
                if (!hasUppercase) missing.push('uppercase letters');
                if (!hasLowercase) missing.push('lowercase letters');
                if (!hasNumbers) missing.push('numbers');
                
                passwordRequirements.textContent = 'Missing: ' + missing.join(', ');
                passwordRequirements.style.color = colors[strength - 1] || '#ff4d4d';
            } else {
                passwordRequirements.textContent = 'Strong password!';
                passwordRequirements.style.color = '#46cc46';
            }
        });
    }

    // Add real-time validation for confirm password
    const confirmPasswordField = document.getElementById('confPassword');
    const passwordError = document.getElementById('password-error');
    
    if (confirmPasswordField) {
        confirmPasswordField.addEventListener('input', function() {
            const confirmPassword = this.value;
            const password = document.getElementById('password').value;
            
            if (confirmPassword !== password && confirmPassword !== '') {
                passwordError.style.display = 'block';
            } else {
                passwordError.style.display = 'none';
            }
        });
    }
});

// Form validation function (called on submit)
function validateResetForm() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confPassword').value;
    
    // Check if passwords match
    if (password !== confirmPassword) {
        document.getElementById('password-error').style.display = 'block';
        return false;
    }
    
    // Validate password strength
    const hasUppercase = /[A-Z]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasNumbers = /\d/.test(password);
    const hasMinLength = password.length >= 8;
    
    if (!hasUppercase || !hasLowercase || !hasNumbers || !hasMinLength) {
        let missing = [];
        if (!hasMinLength) missing.push('at least 8 characters');
        if (!hasUppercase) missing.push('uppercase letters');
        if (!hasLowercase) missing.push('lowercase letters');
        if (!hasNumbers) missing.push('numbers');
        
        alert('Password must include ' + missing.join(', '));
        return false;
    }
    
    return true;
}