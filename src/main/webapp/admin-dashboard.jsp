<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coffee Shop Admin Dashboard</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Sidebar -->
    <div class="sidebar">
        <jsp:include page="admin-sidebar.jsp">
            <jsp:param name="activePage" value="dashboard"/>
        </jsp:include>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div id="notification" class="notification hide"></div>

        <!-- Dashboard Panel -->
        <div id="dashboard-panel" class="panel">
            <div class="panel-header">
                <h2>Dashboard</h2>
            </div>
            <div class="panel-body">
                <div style="display: flex; gap: 20px; margin-bottom: 20px;">
                    <div style="flex: 1; background: #3498db; color: white; padding: 20px; border-radius: 5px; text-align: center;">
                        <h3>Products</h3>
                        <h2 id="product-count">0</h2>
                    </div>
                    <div style="flex: 1; background: #2ecc71; color: white; padding: 20px; border-radius: 5px; text-align: center;">
                        <h3>Categories</h3>
                        <h2 id="category-count">0</h2>
                    </div>
                    <div style="flex: 1; background: #e74c3c; color: white; padding: 20px; border-radius: 5px; text-align: center;">
                        <h3>Orders</h3>
                        <h2>0</h2>
                    </div>
                </div>
                <h3>Recent Products</h3>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Stock</th>
                    </tr>
                    </thead>
                    <tbody id="recent-products">
                    <tr>
                        <td colspan="4">Loading recent products...</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/dashboard.js"></script>
</body>
</html>