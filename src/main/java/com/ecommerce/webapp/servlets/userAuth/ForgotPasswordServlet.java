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
        } else {
            handleForgotPassword(request, response);
        }
    }
    
    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("reset-email");
        String phone = request.getParameter("reset-cc");
        
        if (email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            setErrorAndRedirect(request, response, "Email and phone number are required", "forgot-password.jsp");
            return;
        }
        
        // Verify user exists
        User user = userDAO.findByEmail(email);
        if (user == null) {
            setErrorAndRedirect(request, response, "Email not found in our records", "forgot-password.jsp");
            return;
        }
        
        // Verify phone matches
        if (!phone.equals(user.getPhone())) {
            setErrorAndRedirect(request, response, "Phone number does not match our records", "forgot-password.jsp");
            return;
        }
        
        // Generate reset token and store in session
        String resetToken = generateSecureToken();
        HttpSession session = request.getSession();
        session.setAttribute("resetEmail", email);
        session.setAttribute("resetToken", resetToken);
        session.setMaxInactiveInterval(30 * 60); // 30 minutes
        
        response.sendRedirect("reset-password.jsp");
    }
    
    private void handlePasswordReset(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");
        
        if (email == null) {
            setErrorAndRedirect(request, response, "Password reset session expired or invalid", "forgot-password.jsp");
            return;
        }
        
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            setErrorAndRedirect(request, response, "Passwords do not match", "reset-password.jsp");
            return;
        }
    
        if (userDAO.updatePassword(email, newPassword)) {

            session.removeAttribute("resetEmail");
            session.removeAttribute("resetToken");
            
            session.setAttribute("successMessage", "Password reset successful. Please log in.");
            response.sendRedirect("login.jsp");
        } else {
            setErrorAndRedirect(request, response, "Failed to update password", "reset-password.jsp");
        }
    }
    
    private void setErrorAndRedirect(HttpServletRequest request, HttpServletResponse response, 
                                    String message, String page) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(page).forward(request, response);
    }
    
    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}