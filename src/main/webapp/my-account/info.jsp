<%--
  Created by IntelliJ IDEA.
  User: AHMED
  Date: 2025-04-01
  Time: 05:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white u-s-m-b-30">
    <div class="dash__pad-2">
        <h1 class="dash__h1 u-s-m-b-14">My Account Information</h1>
        <span class="dash__text u-s-m-b-30">From your My Account Dashboard you have the ability to view a snapshot of your recent account activity and update your account information.</span>


        <div class="row">
            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">NAME</h2>
                        <span class="dash__text">${sessionScope.user.name}</span>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">EMAIL</h2>
                        <span class="dash__text">${sessionScope.user.email}</span>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">PHONE NUMBER</h2>
                        <span class="dash__text">${sessionScope.user.phone}</span>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">ADDRESS</h2>
                        <span class="dash__text">${sessionScope.user.address}</span>
                    </div>
                </div>
            </div>


            <div class="col-lg-4 u-s-m-b-30">
                <div class="dash__box dash__box--bg-grey dash__box--shadow-2 u-h-100">
                    <div class="dash__pad-3">
                        <h2 class="dash__h2 u-s-m-b-8">CREDIT</h2>

                        <span class="dash__text">
                            <fmt:formatNumber
                                    value="${sessionScope.user.creditBalance}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>
                        </span>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>