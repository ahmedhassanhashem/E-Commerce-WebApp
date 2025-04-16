package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkEmail")
public class EmailCheckServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String mode = request.getParameter("mode"); // "login" or "register"
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        // Validate email format (basic validation)
        if (email == null || email.trim().isEmpty()) {
            out.print("Please enter an email address");
            return;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            out.print("Please enter a valid email address");
            return;
        }
        
        User user = userDAO.findByEmail(email);
        
        // Different responses based on mode
        if ("register".equals(mode)) {
            // For registration: email should NOT exist
            if (user != null) {
                out.print("User already exists with this email");
            } else {
                out.print("Valid email address for registration");
            }
        } else{
            // For login: email SHOULD exist
            if (user != null) {
                out.print("Valid user!");
            } else {
                out.print("Email not found. Please <a href='register.jsp'>register</a> first");
            }
        }
    }
}