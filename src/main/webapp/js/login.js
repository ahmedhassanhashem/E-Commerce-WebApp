// Function to check email using AJAX
function checkEmail() {
    var email = document.getElementById("login-email").value;
    var validationMessageElement = document.getElementById("email-validation-message");
    
    // Clear previous validation messages
    validationMessageElement.innerHTML = "";
    
    // Skip validation if email is empty
    if (!email.trim()) {
        return;
    }
    
    // Show loading message
    validationMessageElement.innerHTML = "Checking...";
    validationMessageElement.style.color = "blue";
    
    // Create AJAX request
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var response = xhr.responseText;
                
                // Update validation message
                validationMessageElement.innerHTML = response;
                
                // Set color based on if it's a valid user or not
                if (response.includes("Valid user")) {
                    validationMessageElement.style.color = "green";
                    // Focus on password field
                    document.getElementById("login-password").focus();
                } else {
                    validationMessageElement.style.color = "red";
                }
            } else {
                // Handle error
                validationMessageElement.innerHTML = "Error checking email. Please try again.";
                validationMessageElement.style.color = "red";
            }
        }
    };
    
    // Send request to the servlet
    xhr.open("GET", "checkEmail?email=" + encodeURIComponent(email), true);
    xhr.send();
}

// Add event listener when document is loaded
document.addEventListener("DOMContentLoaded", function() {
    // Add event listener to the email field
    var emailField = document.getElementById("login-email");
    if (emailField) {
        emailField.addEventListener("blur", checkEmail);
    }
});
