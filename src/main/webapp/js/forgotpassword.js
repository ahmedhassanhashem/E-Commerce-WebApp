document.addEventListener('DOMContentLoaded', function() {
    setupForgotPasswordValidation();
    setupPasswordStrengthMeter();
});

function setupForgotPasswordValidation() {
    
    // For the email input in forgot password form
    const emailInput = document.getElementById('reset-email');
    if (emailInput) {
        emailInput.addEventListener('blur', function() {
            validateEmail(this.value);
        });
    }
    
    // For the phone input in forgot password form
    const phoneInput = document.getElementById('reset-cc');
    if (phoneInput) {
        phoneInput.addEventListener('blur', function() {
            validatePhone(this.value);
        });
    }
}

function setupPasswordStrengthMeter() {
    // For the new password field in reset password form
    const passwordInput = document.getElementById('new-password');
    const confirmInput = document.getElementById('confirm-password');
    
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            updatePasswordStrength(this.value);
        });
    }
    
    if (confirmInput) {
        confirmInput.addEventListener('input', function() {
            checkPasswordMatch(passwordInput.value, this.value);
        });
    }
}

function validateEmail(email) {
    const errorElement = document.getElementById('email-error');
    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        errorElement.style.display = 'block';
        return false;
    } else {
        errorElement.style.display = 'none';
        
        checkEmailExists(email);
        return true;
    }
}

function validatePhone(phone) {
    const errorElement = document.getElementById('phone-error');
    
    const phoneRegex = /^\d{10,15}$/;
    if (!phoneRegex.test(phone)) {
        errorElement.style.display = 'block';
        return false;
    } else {
        errorElement.style.display = 'none';
        return true;
    }
}

function checkEmailExists() {
    var email = document.getElementById("reset-email").value;
    var validationMessageElement = document.getElementById("email-validation-message");
    
    validationMessageElement.innerHTML = "";
    
    if (!email.trim()) {
        return;
    }
    
    validationMessageElement.innerHTML = "Checking...";
    validationMessageElement.style.color = "blue";
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var response = xhr.responseText;
                
                validationMessageElement.innerHTML = response;
                
                if (response.includes("Valid user")) {
                    validationMessageElement.style.color = "green";
                    document.getElementById("reset-cc").focus();
                } else {
                    validationMessageElement.style.color = "red";
                }
            } else {
                validationMessageElement.innerHTML = "Error checking email. Please try again.";
                validationMessageElement.style.color = "red";
            }
        }
    };
    
    xhr.open("GET", "checkEmail?email=" + encodeURIComponent(email), true);
    xhr.send();
}

function updatePasswordStrength(password) {
    const strengthBar = document.getElementById('password-strength');
    if (!strengthBar) return;
    
    let strength = 0;
    
    if (password.length >= 8) strength += 25;
    
    if (/[A-Z]/.test(password)) strength += 25; // Uppercase
    if (/[a-z]/.test(password)) strength += 25; // Lowercase
    if (/[0-9]/.test(password)) strength += 25; // Numbers
    
    strengthBar.style.width = strength + '%';
    
    if (strength < 50) {
        strengthBar.style.backgroundColor = '#ff4d4d'; // Red
    } else if (strength < 75) {
        strengthBar.style.backgroundColor = '#ffd633'; // Yellow
    } else {
        strengthBar.style.backgroundColor = '#66cc66'; // Green
    }
}

function checkPasswordMatch(password, confirmPassword) {
    const errorElement = document.getElementById('password-error');
    
    if (password !== confirmPassword) {
        errorElement.style.display = 'block';
        return false;
    } else {
        errorElement.style.display = 'none';
        return true;
    }
}

function validateForgotPassEmail() {
    const email = document.getElementById('reset-email').value;
    const phone = document.getElementById('reset-cc').value;
    
    return validateEmail(email) && validatePhone(phone);
}

function validateResetForm() {
    const password = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    
    const hasUppercase = /[A-Z]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasNumbers = /[0-9]/.test(password);
    const isLongEnough = password.length >= 8;
    
    if (!isLongEnough || !hasUppercase || !hasLowercase || !hasNumbers) {
        const requirementsElement = document.getElementById('password-requirements');
        requirementsElement.style.color = '#ff4d4d';
        return false;
    }
    
    return checkPasswordMatch(password, confirmPassword);
}