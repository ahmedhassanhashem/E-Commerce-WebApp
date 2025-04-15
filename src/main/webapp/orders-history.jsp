<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coffee Shop Admin - Orders</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Sidebar -->
    <div class="sidebar">
        <jsp:include page="admin-sidebar.jsp">
            <jsp:param name="activePage" value="orders"/>
        </jsp:include>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div id="notification" class="notification"></div>

        <!-- Orders Panel -->
        <div class="panel">
            <div class="panel-header">
                <h2><i class="fas fa-shopping-cart"></i> Order History</h2>
                <div>
                    <button class="btn btn-primary" onclick="refreshOrders()">
                        <i class="fas fa-sync-alt"></i> Refresh
                    </button>
                </div>
            </div>

            <!-- Search and Filter Section -->
            <div class="search-filter">
                <div class="form-group">
                    <label for="search-order">Search Orders</label>
                    <input type="text" id="search-order" class="form-control" placeholder="Order ID or Customer Email">
                </div>
                <div class="form-group">
                    <label for="filter-status">Filter by Status</label>
                    <select id="filter-status" class="form-control">
                        <option value="all">All Statuses</option>
                        <option value="pending">Pending</option>
                        <option value="Accepted">Accepted</option>
                        <option value="cancelled">Cancelled</option>
                    </select>
                </div>
            </div>

            <div class="panel-body">
                <table id="orders-table">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Items</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Orders will be loaded via AJAX -->
                    <tr>
                        <td colspan="7" class="text-center">Loading orders...</td>
                    </tr>
                    </tbody>
                </table>

                <!-- Pagination -->
                <div class="pagination" id="pagination">
                    <!-- Pagination will be loaded via AJAX -->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="js/orders.js"></script>
</body>
</html>