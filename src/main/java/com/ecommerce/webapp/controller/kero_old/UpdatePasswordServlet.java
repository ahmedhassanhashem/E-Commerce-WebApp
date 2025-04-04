package com.ecommerce.webapp.controller.kero_old;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.ecommerce.webapp.model.kero.User;

@WebServlet("/update-password")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(401);
            out.print("{\"success\": false, \"message\": \"Session expired\"}");
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.setStatus(401);
            out.print("{\"success\": false, \"message\": \"Session expired\"}");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate current password
        if (!currentUser.getPassword().equals(currentPassword)) {
            response.setStatus(400);
            out.print("{\"success\": false, \"message\": \"Current password is incorrect\"}");
            return;
        }

        // Validate new passwords match
        if (!newPassword.equals(confirmPassword)) {
            response.setStatus(400);
            out.print("{\"success\": false, \"message\": \"New passwords don't match\"}");
            return;
        }

        // Check if new password is the same as current password
        if (newPassword.equals(currentPassword)) {
            response.setStatus(400);
            out.print("{\"success\": false, \"message\": \"New password cannot be the same as your current password\"}");
            return;
        }

        // Update session
        currentUser.setPassword(newPassword);
        session.setAttribute("currentUser", currentUser);

        response.setStatus(200);
        out.print("{\"success\": true, \"message\": \"Password updated successfully\"}");
    }
}
