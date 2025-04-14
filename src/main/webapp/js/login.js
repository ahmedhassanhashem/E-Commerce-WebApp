function checkEmail() {
    var email = document.getElementById("login-email").value;
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
    
    xhr.open("GET", "checkEmail?email=" + encodeURIComponent(email), true);
    xhr.send();
}

document.addEventListener("DOMContentLoaded", function() {
    var emailField = document.getElementById("login-email");
    if (emailField) {
        emailField.addEventListener("blur", checkEmail);
    }
});
