<!--====== Dashboard Features ======-->
<div class="dash__box dash__box--bg-white dash__box--shadow u-s-m-b-30">
    <div class="dash__pad-1">
        <ul class="dash__f-list">
            <li>
                <a href="dashboard.jsp"
                   class="<%= "dashboard".equals(request.getAttribute("activePage")) ? "dash-active" : "" %>">
                My Account
                </a>
            </li>
            <li>
                <a href="dash-edit-profile.jsp"
                   class="<%= "edit-profile".equals(request.getAttribute("activePage")) ? "dash-active" : "" %>">
                Edit Profile
                </a>
            </li>
            <li>
                <a href="dash-my-order.jsp"
                   class="<%= "my-orders".equals(request.getAttribute("activePage")) ? "dash-active" : "" %>">
                My Orders
                </a>
            </li>
            <!--            <li><a href="my-balance.jsp">My Balance</a></li>-->
        </ul>
    </div>
</div>













