document.addEventListener('DOMContentLoaded', function() {
    setupForgotPasswordValidation();
});

function setupForgotPasswordValidation() {
    
    // For the email input in forgot password form
    const emailInput = document.getElementById('reset-email');
    if (emailInput) {
        emailInput.addEventListener('blur', function() {
            validateEmail(this.value);
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
function validateForgotPassEmail() {
    const email = document.getElementById('reset-email').value;
    const phone = document.getElementById('reset-cc').value;
    
    return validateEmail(email) && validatePhone(phone);
}
