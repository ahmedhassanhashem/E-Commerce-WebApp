// function checkEmail() {
//     var email = document.getElementById("login-email").value;
//     var validationMessageElement = document.getElementById("email-validation-message");
    
//     validationMessageElement.innerHTML = "";
    
//     if (!email.trim()) {
//         return;
//     }
    
//     validationMessageElement.innerHTML = "Checking...";
//     validationMessageElement.style.color = "blue";
    
//     var xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function() {
//         if (xhr.readyState == 4) {
//             if (xhr.status == 200) {
//                 var response = xhr.responseText;
                
//                 validationMessageElement.innerHTML = response;
                
//                 if (response.includes("Valid user")) {
//                     validationMessageElement.style.color = "green";
//                     document.getElementById("login-password").focus();
//                 } else {
//                     validationMessageElement.style.color = "red";
//                 }
//             } else {
//                 validationMessageElement.innerHTML = "Error checking email. Please try again.";
//                 validationMessageElement.style.color = "red";
//             }
//         }
//     };
    
//     xhr.open("GET", "checkEmail?email=" + encodeURIComponent(email), true);
//     xhr.send();
// }

// document.addEventListener("DOMContentLoaded", function() {
//     var emailField = document.getElementById("login-email");
//     if (emailField) {
//         emailField.addEventListener("blur", checkEmail);
//     }
// });
document.addEventListener("DOMContentLoaded", function() {
    var emailField = document.getElementById("login-email");
    if (emailField) {
        emailField.addEventListener("blur", function() {
            checkEmail('login');
        });
    }
    
    const form = document.querySelector('.l-f-o__form');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            
            // Clear any previous error messages
            clearErrorMessages();
            
            const email = document.getElementById('login-email').value.trim();
            const password = document.getElementById('login-password').value;
            
            // Validation flags
            let isValid = true;
            
            // Validate email
            if (!validateEmail(email)) {
                displayError('login-email', 'Please enter a valid email address');
                isValid = false;
            }
            
            // Validate password (not empty)
            if (password.length === 0) {
                displayError('login-password', 'Please enter your password');
                isValid = false;
            }
            
            // If all validations pass, submit the form
            if (isValid) {
                form.submit();
            }
        });
    }
});

function checkEmail(mode) {
    var email = document.getElementById("login-email").value;
    var validationMessageElement = document.getElementById("email-validation-message");
    
    validationMessageElement.innerHTML = "";
    
    if (!email.trim()) {
        return;
    }
    
    // Check email format first
    if (!validateEmail(email)) {
        validationMessageElement.innerHTML = "Please enter a valid email format";
        validationMessageElement.style.color = "red";
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
                    document.getElementById("login-password").focus();
                } else {
                    validationMessageElement.style.color = "red";
                }
            } else {
                validationMessageElement.innerHTML = "Error checking email. Please try again.";
                validationMessageElement.style.color = "red";
            }
        }
    };
    
    // Add mode parameter to the request
    xhr.open("GET", "checkEmail?email=" + encodeURIComponent(email) + "&mode=" + mode, true);
    xhr.send();
}

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function displayError(inputId, message) {
    const input = document.getElementById(inputId);
    
    // Check if error message already exists
    const existingError = input.parentNode.querySelector('.error-message');
    if (existingError) {
        existingError.textContent = message;
        return;
    }
    
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
