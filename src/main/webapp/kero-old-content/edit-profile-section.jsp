<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>

<div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white">
    <div class="dash__pad-2">
        <h1 class="dash__h1 u-s-m-b-14">Edit Profile</h1>

        <div class="row">
            <div class="col-lg-12">
                <form class="dash-edit-p" action="update-profile" method="POST" id="profileForm">
                    <div class="gl-inline">
                        <div class="u-s-m-b-30">
                            <label class="gl-label" for="reg-fname">FIRST NAME</label>
                            <input class="input-text input-text--primary-style"
                                   type="text"
                                   id="reg-fname"
                                   name="firstName"
                                   value="${currentUser.firstName}">
                            <div class="error-message" id="fname-error"></div>
                        </div>

                        <div class="u-s-m-b-30">
                            <label class="gl-label" for="reg-lname">LAST NAME</label>
                            <input class="input-text input-text--primary-style"
                                   type="text"
                                   id="reg-lname"
                                   name="lastName"
                                   value="${currentUser.lastName}">
                            <div class="error-message" id="lname-error"></div>
                        </div>
                    </div>


                    <div class="gl-inline">
                        <div class="u-s-m-b-30">

                            <!--====== Date of Birth Select-Box ======-->

                            <span class="gl-label">BIRTHDAY</span>
                            <div class="gl-dob">

                                <!-- Month Select -->
                                <select class="select-box select-box--primary-style" id="birth-month" name="birthMonth">
                                    <option value="1">January</option>
                                    <option value="2">February</option>
                                    <option value="3">March</option>
                                    <option value="4">April</option>
                                    <option value="5">May</option>
                                    <option value="6">June</option>
                                    <option value="7">July</option>
                                    <option value="8">August</option>
                                    <option value="9">September</option>
                                    <option value="10">October</option>
                                    <option value="11">November</option>
                                    <option value="12">December</option>
                                </select>

                                <!-- Day Select -->
                                <select class="select-box select-box--primary-style" id="birth-day" name="birthDay">
                                    <c:forEach begin="1" end="31" var="day">
                                        <option value="${day}">${day < 10 ? '0' : ''}${day}</option>
                                    </c:forEach>
                                </select>

                                <!-- Year Select -->
                                <select class="select-box select-box--primary-style" id="birth-year" name="birthYear">
                                    <c:forEach begin="1950" end="2050" var="year" step="1">
                                        <option value="${year}">${year}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!--====== End - Date of Birth Select-Box ======-->
                        </div>
                        <div class="u-s-m-b-30">

                            <label class="gl-label" for="gender">GENDER</label>
                            <select class="select-box select-box--primary-style u-w-100" id="gender" name="gender">
                                <option value="male" ${currentUser.gender == 'male' ? 'selected' : ''}>Male</option>
                                <option value="female" ${currentUser.gender == 'female' ? 'selected' : ''}>Female</option>
                            </select>
                        </div>
                    </div>


                     <div class="gl-inline">
                        <div class="u-s-m-b-30">
                            <h2 class="dash__h2 u-s-m-b-8">E-mail</h2>
                            <input class="input-text input-text--primary-style"
                                   type="email"
                                   id="reg-email"
                                   name="email"
                                   value="${currentUser.email}">
                        <div class="error-message" id="email-error"></div>
                    </div>

                    <div class="u-s-m-b-30">
                        <h2 class="dash__h2 u-s-m-b-8">Phone</h2>
                        <input class="input-text input-text--primary-style"
                               type="text"
                               id="reg-phone"
                               name="phone"
                               value="${currentUser.phone}"
                               maxlength="11">
                        <div class="error-message" id="phone-error"></div>
                    </div>
                </div>

                    <div class="gl-inline">
                        <div class="u-s-m-b-30">
                            <h2 class="dash__h2 u-s-m-b-8">Address</h2>

                            <input class="input-text input-text--primary-style" type="text" id="reg-address" name="address" value="${currentUser.address}">
                        </div>
                    </div>


                    <button class="btn btn--e-brand-b-2" type="submit">SAVE</button>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
document.addEventListener('DOMContentLoaded', function() {
    // Initialize birthdate
    var birthDay = ${currentUser.birthDate != null ? currentUser.birthDate.getDate() : 0};
    var birthMonth = ${currentUser.birthDate != null ? currentUser.birthDate.getMonth() + 1 : 0};
    var birthYear = ${currentUser.birthDate != null ? currentUser.birthDate.getYear() + 1900 : 0};

    if (birthDay > 0) document.getElementById('birth-day').value = birthDay;
    if (birthMonth > 0) document.getElementById('birth-month').value = birthMonth;
    if (birthYear > 0) document.getElementById('birth-year').value = birthYear;

    // Validation patterns
        const patterns = {
            name: /^[A-Za-z ]{2,50}$/,
            email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/,
            phone: /^01[0125]\d{8}$/
        };

        // Real-time validation handlers
        document.getElementById('reg-fname').addEventListener('input', function(e) {
            validateField(e.target, patterns.name, 'fname-error',
                'Only letters and spaces (2-50 characters)');
        });

        document.getElementById('reg-lname').addEventListener('input', function(e) {
            validateField(e.target, patterns.name, 'lname-error',
                'Only letters and spaces (2-50 characters)');
        });

        document.getElementById('reg-email').addEventListener('input', function(e) {
            validateField(e.target, patterns.email, 'email-error',
                'Invalid email format (e.g., user@example.com)');
        });

        document.getElementById('reg-phone').addEventListener('input', function(e) {
            validateField(e.target, patterns.phone, 'phone-error',
                'Must start with 010, 011, 012, or 015 followed by 8 digits');
        });

        // Generic validation function
        function validateField(field, pattern, errorId, message) {
            const value = field.value.trim();
            const errorElement = document.getElementById(errorId);

            if (pattern.test(value)) {
                errorElement.textContent = '';
                errorElement.style.display = 'none';
                field.classList.remove('input-error');
            } else {
                errorElement.textContent = message;
                errorElement.style.display = 'block';
                field.classList.add('input-error');
            }
        }

        // Form submission handler
        document.getElementById('profileForm').addEventListener('submit', function(e) {
            e.preventDefault();
            let isValid = true;

            // Validate all fields
            isValid = validateField(document.getElementById('reg-fname'), patterns.name, 'fname-error',
                       'Only letters and spaces (2-50 characters)') && isValid;

            isValid = validateField(document.getElementById('reg-lname'), patterns.name, 'lname-error',
                       'Only letters and spaces (2-50 characters)') && isValid;

            isValid = validateField(document.getElementById('reg-email'), patterns.email, 'email-error',
                       'Invalid email format (e.g., user@example.com)') && isValid;

            isValid = validateField(document.getElementById('reg-phone'), patterns.phone, 'phone-error',
                       'Must start with 010, 011, 012, or 015 followed by 8 digits') && isValid;

            if (isValid) {
                this.submit();
            }
        });

        // Update validateField to return validation status
        function validateField(field, pattern, errorId, message) {
            const value = field.value.trim();
            const errorElement = document.getElementById(errorId);
            const isValid = pattern.test(value);

            if (!isValid) {
                errorElement.textContent = message;
                errorElement.style.display = 'block';
                field.classList.add('input-error');
            } else {
                errorElement.textContent = '';
                errorElement.style.display = 'none';
                field.classList.remove('input-error');
            }

            return isValid;
        }
    });
    </script>

    <style>
    .error-message {
        color: #dc3545;
        font-size: 0.875rem;
        margin-top: 0.25rem;
        display: none;
    }

    .input-error {
        border: 2px solid #dc3545 !important;
    }
    </style>




