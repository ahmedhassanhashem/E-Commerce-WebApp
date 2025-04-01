<%--
  Created by IntelliJ IDEA.
  User: AHMED
  Date: 2025-04-01
  Time: 05:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="dash__box dash__box--shadow dash__box--bg-white dash__box--radius">
    <h2 class="dash__h2 u-s-p-xy-20">RECENT ORDERS</h2>
    <div class="dash__table-wrap gl-scroll">
        <table class="dash__table">
            <thead>
            <tr>
                <th>Order #</th>
                <th>Items</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>3054231326</td>
                <td>
                    <ul class="order-items" style="list-style: decimal">
                        <li>Coffee</li>
                        <li>Mug</li>
                        <li>Maker</li>
                    </ul>
                </td>
                <td>
                    <ul class="order-items" style="list-style: none">
                        <li>2</li>
                        <li>4</li>
                        <li>1</li>
                    </ul>
                </td>
                <td>$126.00</td>
                <td>
                    <span class="manage-o__badge badge--processing">Processing</span>

                    <div class="dash__link dash__link--brand">
                        <a href="dash-manage-order.html">MANAGE</a>
                    </div>
                </td>

            </tr>
            </tbody>
        </table>
    </div>
</div>

