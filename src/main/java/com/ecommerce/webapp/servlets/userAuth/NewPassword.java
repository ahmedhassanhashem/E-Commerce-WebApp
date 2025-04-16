package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        
        // Validate OTP verification status
        Boolean otpVerified = (Boolean) session.getAttribute("otpVerified");
        if (otpVerified == null || !otpVerified) {
            request.setAttribute("errorMessage", "Invalid access. Please complete the verification process first.");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Get form data
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = request.getParameter("email");
        
        // Validate inputs
        if (password == null || confPassword == null || email == null || 
            password.trim().isEmpty() || confPassword.trim().isEmpty() || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required");
            request.setAttribute("status", "success"); // To keep the form visible
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Validate password match
        if (!password.equals(confPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.setAttribute("status", "success"); // To keep the form visible
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Validate password strength
        if (password.length() < 8) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long");
            request.setAttribute("status", "success"); // To keep the form visible
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
       
        // Update password in database
        UserDAO userDAO = new UserDAO();
        boolean updated = userDAO.updatePassword(email, hashedPassword);
        
        if (updated) {
            // Clear reset-related session attributes
            session.removeAttribute("otp");
            session.removeAttribute("otpTimestamp");
            session.removeAttribute("otpVerified");
            
            // Fetch user data to create login session
           User user = userDAO.findByEmail(email);
            
            if (user != null) {
                // Create login session
                session.setAttribute("user", user);
                session.setAttribute("authenticated", true);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userName", user.getName());
                
                // Flash message for home page
                session.setAttribute("successMessage", "Your password has been reset successfully.");
                
                // Redirect to home page
                response.sendRedirect("home");
            } else {
                // Fallback if user can't be retrieved
                session.setAttribute("successMessage", "Password has been reset successfully. Please login with your new password.");
                response.sendRedirect("login.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "Failed to update password. Please try again.");
            request.setAttribute("status", "success"); // To keep the form visible
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}