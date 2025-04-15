package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.UserDAO;
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
import java.util.Arrays;
import java.util.List;
import com.ecommerce.webapp.entities.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    private static final List<String> ADMIN_EMAILS = Arrays.asList(
        "hadeer@gmail.com",
        "ahmed@gmail.com",
        "kerollos@gmail.com",
        "admin@gmail.com"
    );
    
    private static final Map<String, String> REMEMBER_ME_TOKENS = new HashMap<>();
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember-me");
        
        if (userDAO.validate(email, password)) {
            
            User user = userDAO.findByEmail(email);
            
            // Create user data for session
            Map<String, Object> userData = new HashMap<>();
            userData.put("email", user.getEmail());
            userData.put("name", user.getName() != null ? user.getName() : user.getEmail().split("@")[0]);
            
            // Check if user is admin based on static email list
            boolean isAdmin = ADMIN_EMAILS.contains(email);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", userData);
            session.setAttribute("isAdmin", isAdmin);
            
            if (rememberMe != null) {
                String token = generateSecureToken();
                
                REMEMBER_ME_TOKENS.put(token, email);
                
                // Create persistent cookie
                Cookie rememberMeCookie = new Cookie("rememberMeToken", token);
                rememberMeCookie.setMaxAge(60*60*24*30); // 30 days
                rememberMeCookie.setPath("/");
                rememberMeCookie.setHttpOnly(true); // For security
                response.addCookie(rememberMeCookie);
            }
            
            if (isAdmin) {
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                response.sendRedirect("home");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}