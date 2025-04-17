function moveToNext(current, index) {
    // Allow only one digit
    if (current.value.length > 1) {
        current.value = current.value.slice(0, 1);
    }

    // Move to next input if a digit is entered
    if (current.value.length === 1 && index < 5) {
        current.nextElementSibling.focus();
    }

    // Handle backspace to move to previous input
    if (current.value.length === 0 && index > 0 && event.inputType === "deleteContentBackward") {
        current.previousElementSibling.focus();
    }

    // Ensure only numbers are entered
    if (current.value && !/^[0-9]$/.test(current.value)) {
        current.value = "";
    }
}

// Combine OTP digits before form submission
document.getElementById("otpForm").addEventListener("submit", function(event) {
    const inputs = document.querySelectorAll(".otp-input");
    let otp = "";
    inputs.forEach(input => {
        otp += input.value;
    });
    document.getElementById("otp").value = otp;
});