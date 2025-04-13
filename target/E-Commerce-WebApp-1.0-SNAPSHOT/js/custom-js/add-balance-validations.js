function addBalance(event) {
    event.preventDefault();

    const creditInput = document.getElementById("reg-credit");
    const errorDisplay = document.getElementById("credit-error");
    const amount = creditInput.value.trim();

    errorDisplay.style.display = 'none';
    creditInput.classList.remove('input-error');

    const decimalRegex = /^[+]?\d+(\.\d{1,2})?$/;

    if (!decimalRegex.test(amount) || parseFloat(amount) <= 0) {
        showError("Please enter a valid positive number with two decimal places (e.g., 50.00)");
        return;
    }

    fetch('add-balance', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'credit=' + encodeURIComponent(amount)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                const balanceDisplay = document.getElementById("current-credit");
                if (balanceDisplay) {
                    balanceDisplay.textContent = data.newBalance.toFixed(2);
                }
                creditInput.value = "";
            }

            // Wait for next paint before showing alert
            requestAnimationFrame(() => {
                setTimeout(() => {
                    alert(data.message);
                }, 0);
            });
        })
        .catch(error => {
            alert("Error updating balance: " + error);
        });

    function showError(message) {
        errorDisplay.textContent = message;
        errorDisplay.style.display = 'block';
        creditInput.classList.add('input-error');
        creditInput.focus();
    }
}

// Real-time validation: allow only valid numeric input
document.getElementById('reg-credit').addEventListener('input', function () {
    this.value = this.value.replace(/[^0-9.]/g, '');
    if ((this.value.match(/\./g) || []).length > 1) {
        this.value = this.value.slice(0, -1);
    }
});

// Bind form submit to addBalance
document.getElementById('profileForm').addEventListener('submit', addBalance);
