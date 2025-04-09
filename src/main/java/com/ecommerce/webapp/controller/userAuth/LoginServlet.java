package com.ecommerce.webapp.controller.userAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    // Dummy credentials map (email -> password)
    private static final Map<String, String> VALID_CREDENTIALS = new HashMap<>();
    
    // Token storage (in a real app, this would be in a database)
    private static final Map<String, String> REMEMBER_ME_TOKENS = new HashMap<>();
    
    static {
        VALID_CREDENTIALS.put("admin@gmail.com", "123");
        VALID_CREDENTIALS.put("user@gmail.com", "123");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember-me");
        
        // Check if credentials are valid
        if (email != null && password != null && 
            VALID_CREDENTIALS.containsKey(email) && 
            VALID_CREDENTIALS.get(email).equals(password)) {
            
            // Create dummy user data for session
            Map<String, Object> userData = new HashMap<>();
            userData.put("email", email);
            userData.put("name", email.split("@")[0]); // Just use first part of email as name
            
            // Check if user is admin
            boolean isAdmin = "admin@gmail.com".equals(email);
            
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", userData);
            session.setAttribute("isAdmin", isAdmin);
            
            // Handle "Remember Me" functionality
            if (rememberMe != null) {
                // Generate a secure token
                String token = generateSecureToken();
                
                // In a real application, store in database with user ID and expiration
                REMEMBER_ME_TOKENS.put(token, email);
                
                // Create persistent cookie
                Cookie rememberMeCookie = new Cookie("rememberMeToken", token);
                rememberMeCookie.setMaxAge(60*60*24*30); // 30 days
                rememberMeCookie.setPath("/");
                rememberMeCookie.setHttpOnly(true); // For security
                response.addCookie(rememberMeCookie);
            }
            
            // Redirect based on user role
            if (isAdmin) {
                response.sendRedirect("admin/admin-dashboard.jsp");
            } else {
                response.sendRedirect("home");
            }
        } else {
            // Set error message and forward back to login page
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    // Helper method to generate a secure random token
    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}