package com.ecommerce.webapp.servlets.Admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class adminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request for admin dashboard
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
    }
    

}
