package com.ecommerce.webapp.servlets.editProfile;

import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;

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

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            out.print("{\"success\": false, \"message\": \"Session expired\"}");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");


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

        // Update password in database
        UserDAO userDAO = new UserDAO();
        String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
        boolean updated = userDAO.updatePassword(user.getEmail(), newHashedPassword);

        if (updated) {
            // Update session
            user.setPassword(newHashedPassword);
            session.setAttribute("user", user);

            response.setStatus(200);
            out.print("{\"success\": true, \"message\": \"Password updated successfully\"}");
        } else {
            response.setStatus(500);
            out.print("{\"success\": false, \"message\": \"Failed to update password in database\"}");
        }
    }
}