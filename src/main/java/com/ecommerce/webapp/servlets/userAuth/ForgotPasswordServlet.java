package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("reset".equals(action)) {
            handlePasswordReset(request, response);
        } 
        else {
            handleForgotPassword(request, response);
        }
    }
    
    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("reset-email");
        String phone = request.getParameter("reset-cc");
        
        if (email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email and phone number are required");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        
        User user = userDAO.findByEmail(email);
        
        // Verify user exists and phone matches
        if (user == null) {
            request.setAttribute("errorMessage", "Email not found in our records");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        
        // Check if phone matches the user's record
        if (user.getPhone() == null || !user.getPhone().equals(phone)) {
            request.setAttribute("errorMessage", "Phone number does not match our records for this email");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        
        String resetToken = generateSecureToken();
      
        HttpSession session = request.getSession();
        session.setAttribute("resetEmail", email);
        session.setAttribute("resetToken", resetToken);
        
        session.setMaxInactiveInterval(30 * 60);
        
        response.sendRedirect("reset-password.jsp");
    }
    
    private void handlePasswordReset(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");
        String token = (String) session.getAttribute("resetToken");
        
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");
        
        // Validate inputs and session data
        if (email == null || token == null) {
            request.setAttribute("errorMessage", "Password reset session expired or invalid. Please try again.");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        
        if (newPassword == null || confirmPassword == null) {
            request.setAttribute("errorMessage", "Missing required parameters");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }
        
        // Verify passwords match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }
        
        // Validate password strength
        boolean isStrongPassword = newPassword.length() >= 8 &&
                                 newPassword.matches(".*[A-Z].*") && 
                                 newPassword.matches(".*[a-z].*") && 
                                 newPassword.matches(".*[0-9].*");
        
        if (!isStrongPassword) {
            request.setAttribute("errorMessage", 
                "Password must be at least 8 characters long and include uppercase, lowercase, and numbers");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }
        
        boolean updateSuccess = userDAO.updatePassword(email, newPassword);
        
        if (updateSuccess) {

            session.removeAttribute("resetEmail");
            session.removeAttribute("resetToken");
            
            session.setAttribute("successMessage", "Password has been reset successfully. Please log in with your new password.");
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to update password. Please try again.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }
    
    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}