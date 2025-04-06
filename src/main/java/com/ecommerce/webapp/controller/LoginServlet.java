// package com.ecommerce.webapp.controller;

// import com.ecommerce.webapp.dao.UserDAO;
// import com.ecommerce.webapp.model.User;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;
// import java.io.IOException;

// @WebServlet("/login")
// public class LoginServlet extends HttpServlet {
    
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//             throws ServletException, IOException {
        
//         String email = request.getParameter("email");
//         String password = request.getParameter("password");
        
//         UserDAO userDao = new UserDAO();
        
//         if (userDao.validate(email, password)) {
//             // Get the user
//             User user = userDao.findByEmail(email);
            
//             // Create session
//             HttpSession session = request.getSession();
//             session.setAttribute("user", user);
            
//             // Redirect to home page or dashboard
//             response.sendRedirect("index.jsp");
//         } else {
//             // Set error message and forward back to login page
//             request.setAttribute("errorMessage", "Invalid email or password");
//             request.getRequestDispatcher("login.jsp").forward(request, response);
//         }
//     }
// }
package com.ecommerce.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    // Dummy credentials map (email -> password)
    private static final Map<String, String> VALID_CREDENTIALS = new HashMap<>();
    
    static {

        VALID_CREDENTIALS.put("admin@gmail.com", "123");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Check if credentials are valid
        if (email != null && password != null && 
            VALID_CREDENTIALS.containsKey(email) && 
            VALID_CREDENTIALS.get(email).equals(password)) {
            
            // Create dummy user data for session
            Map<String, Object> userData = new HashMap<>();
            userData.put("email", email);
            userData.put("name", email.split("@")[0]); // Just use first part of email as name
            
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", userData);
            
            // Redirect to home page or dashboard
            response.sendRedirect("index.jsp");

        } else {
            // Set error message and forward back to login page
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}