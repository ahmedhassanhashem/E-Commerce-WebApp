<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
    <div class="dash__pad-2">
        <h1 class="dash__h1 u-s-m-b-14">My Balance</h1>

        <span class="dash__text u-s-m-b-30">Here you can manage your balance.</span>
        <div class="row">

            <div class="col-lg-8 u-s-m-b-30">
                <h2 class="dash__h2 u-s-m-b-8">Add to Balance</h2>
                <div class="dash__form-wrap">
                    <input id="amountInput"
                           class="input-text input-text--primary-style"
                           type="text"
                           name="amount"
                           placeholder="Enter positive amount (e.g., 50.00)"
                           required
                           pattern="^\d+(\.\d{1,2})?$">
                    <div id="amountError" class="error-message" style="color: red; display: none;"></div>
                </div>

                <button class="btn btn--e-brand-b-2" onclick="addBalance(event)">Add</button>


            </div>

            <div class="col-lg-4 u-s-m-b-30">
                <h2 class="dash__h2 u-s-m-b-8">Current Balance</h2>
                <span class="dash__text">
                    <fmt:formatNumber value="${currentUser.balance}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>
                </span>
            </div>

            <script>
                function addBalance(event) {
                    event.preventDefault(); // Prevent form submission
                    const amountInput = document.getElementById("amountInput");
                    const errorDisplay = document.getElementById("amountError");
                    const amount = amountInput.value.trim();

                    // Reset error state
                    errorDisplay.style.display = 'none';
                    amountInput.classList.remove('input-error');

                    // Validation regex
                    const decimalRegex = /^[+]?\d+(\.\d{1,2})?$/;

                    if (!decimalRegex.test(amount)) {
                        showError("Please enter a valid positive number (e.g., 50.00)");
                        return;
                    }

                    const numericValue = parseFloat(amount);
                    if (numericValue <= 0) {
                        showError("Amount must be greater than zero");
                        return;
                    }

                    // If validation passes, proceed with request
                    fetch('add-balance', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: 'amount=' + encodeURIComponent(numericValue)
                    })
                    .then(response => response.text())
                    .then(data => {
                        alert(data);
                        window.location.reload();
                    })
                    .catch(error => {
                        alert("Error updating balance: " + error);
                    });

                    function showError(message) {
                        errorDisplay.textContent = message;
                        errorDisplay.style.display = 'block';
                        amountInput.classList.add('input-error');
                        amountInput.focus();
                    }
                }

                // Real-time input validation
                document.getElementById('amountInput').addEventListener('input', function(e) {
                    // Allow only numbers and single decimal point
                    this.value = this.value.replace(/[^0-9.]/g, '');

                    // Ensure only one decimal point
                    if ((this.value.match(/\./g) || []).length > 1) {
                        this.value = this.value.slice(0, -1);
                    }
                });
            </script>
        </div>
    </div>
</div>

