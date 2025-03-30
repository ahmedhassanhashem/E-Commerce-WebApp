<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
    <div class="dash__pad-2">
        <h1 class="dash__h1 u-s-m-b-14">My Account Information</h1>
        <span class="dash__text u-s-m-b-30">From your My Account Dashboard you have the ability to view a snapshot of your recent account activity and update your account information.</span>

        <div class="row">
            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">FIRST NAME</h2>
                        <span class="dash__text">${currentUser.firstName}</span>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">LAST NAME</h2>
                        <span class="dash__text">${currentUser.lastName}</span>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">BALANCE</h2>
                        <span class="dash__text">
                            <fmt:formatNumber value="${currentUser.balance}" type="number" minFractionDigits="2"  maxFractionDigits="2"/>
                        </span>
                    </div>
                </div>
            </div>
        </div>




        <div class="row">
                    <div class="col-lg-4 u-s-m-b-30">
                                    <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                                        <div class="dash__pad-3">
                                            <h2 class="dash__h2 u-s-m-b-8">ADDRESS</h2>
                                            <span class="dash__text">${currentUser.address}</span>
                                        </div>
                                    </div>
                    </div>


                    <div class="col-lg-4 u-s-m-b-30">
                        <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                            <div class="dash__pad-3">
                                <h2 class="dash__h2 u-s-m-b-8">EMAIL</h2>
                                <span class="dash__text">${currentUser.email}</span>
                            </div>
                        </div>
                    </div>


                    <div class="col-lg-4 u-s-m-b-30">
                        <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                            <div class="dash__pad-3">
                                <h2 class="dash__h2 u-s-m-b-8">PHONE NUMBER</h2>
                                <span class="dash__text"> 0${currentUser.phone}</span>
                            </div>
                        </div>
                    </div>
        </div>


        <div class="row">
                            <div class="col-lg-4 u-s-m-b-30">
                                            <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                                                <div class="dash__pad-3">
                                                    <h2 class="dash__h2 u-s-m-b-8">BIRTH DATE</h2>
                                                    <span class="dash__text">${currentUser.birthDate}</span>
                                                </div>
                                            </div>
                            </div>


                            <div class="col-lg-4 u-s-m-b-30">
                                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                                    <div class="dash__pad-3">
                                        <h2 class="dash__h2 u-s-m-b-8">GENDER</h2>
                                        <span class="dash__text">${currentUser.gender}</span>
                                    </div>
                                </div>
                            </div>
        </div>
    </div>
</div>