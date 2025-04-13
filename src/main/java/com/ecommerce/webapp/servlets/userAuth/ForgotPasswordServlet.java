package com.ecommerce.webapp.servlets.userAuth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    
    private static final Map<String, ResetInfo> resetTokens = new HashMap<>();
    
    private static final int TOKEN_EXPIRATION_MINUTES = 30;
    
    // Inner class to store reset token information
    private static class ResetInfo {
        String email;
        LocalDateTime expiryTime;
        
        ResetInfo(String email) {
            this.email = email;
            this.expiryTime = LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_MINUTES);
        }
        
        boolean isExpired() {
            return LocalDateTime.now().isAfter(expiryTime);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String token = request.getParameter("token");
        String email = request.getParameter("email");
        
        if (token != null && email != null) {
            if (isValidResetToken(token, email)) {
                request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("errorMessage", "Invalid or expired password reset link. Please try again.");
                request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
                return;
            }
        }
        
        // If no parameters, just show the forgot password page
        response.sendRedirect(request.getContextPath() + "/forgot-password.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check if this is an initial request or a password reset action
        String action = request.getParameter("action");
        
        if ("reset".equals(action)) {
            // This is a password reset submission
            handlePasswordReset(request, response);
        } else {
            // This is an initial forgot password request
            handleForgotPasswordRequest(request, response);
        }
    }
    
    private void handleForgotPasswordRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("reset-email");
        String phone = request.getParameter("reset-cc");
        
        if (email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please provide both email and phone number.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);
        
        if (user == null || !user.getPhone().equals(phone)) {
            request.setAttribute("errorMessage", "No account found with the provided email and phone number.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }
        
        String resetToken = generateSecureToken();
        
        resetTokens.put(resetToken, new ResetInfo(email));
        
        // Create the reset URL
        String resetUrl = request.getContextPath() + "/forgotPassword?token=" + resetToken + "&email=" + email;
        
        response.sendRedirect(resetUrl);
    }
    
    private void handlePasswordReset(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");
        
        if (token == null || email == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("errorMessage", "Missing required parameters.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }
        
        if (!isValidResetToken(token, email)) {
            request.setAttribute("errorMessage", "Invalid or expired reset token.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }
        
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.updatePassword(email, newPassword);
        
        if (success) {
            resetTokens.remove(token);
            
            request.getSession().setAttribute("successMessage", "Password has been successfully reset. Please log in with your new password.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to update password. Please try again.");
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
        }
    }
    
    private String generateSecureToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 256 bits
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    
    private boolean isValidResetToken(String token, String email) {
        ResetInfo info = resetTokens.get(token);
        if (info == null) {
            return false;
        }
        
        if (info.isExpired()) {
            resetTokens.remove(token);
            return false;
        }
        
        return info.email.equals(email);
    }
}