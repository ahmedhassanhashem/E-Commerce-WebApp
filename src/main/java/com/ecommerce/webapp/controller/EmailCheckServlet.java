// package com.ecommerce.webapp.controller;

// import com.ecommerce.webapp.dao.UserDAO;

// import com.ecommerce.webapp.model.User;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.PrintWriter;

// @WebServlet("/checkEmail")
// public class EmailCheckServlet extends HttpServlet {
    
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//             throws ServletException, IOException {
        
//         String email = request.getParameter("email");
//         response.setContentType("text/plain");
//         PrintWriter out = response.getWriter();
        
//         // Validate email format (basic validation)
//         if (email == null || email.trim().isEmpty()) {
//             out.print("Please enter an email address");
//             return;
//         }
        
//         if (!email.contains("@") || !email.contains(".")) {
//             out.print("Please enter a valid email address");
//             return;
//         }
        
//         // Check if email exists in database
//         UserDAO userDao = new UserDAO();
//         User user = userDao.findByEmail(email);
        
//         if (user != null) {
//             out.print("Valid user! Please enter your password");
//         } else {
//             out.print("Email not found. Please <a href='register.jsp'>register</a> first");
//         }
//     }
// }
package com.ecommerce.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/checkEmail")
public class EmailCheckServlet extends HttpServlet {
    
    // List of dummy valid emails for testing
    private static final List<String> VALID_EMAILS = Arrays.asList(
        "user@gmail.com",
        "admin@gmail.com"
    );
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
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
        
        // Check if email exists in our dummy list
        if (VALID_EMAILS.contains(email.toLowerCase())) {
            out.print("Valid user! Please enter your password");
        } else {
            out.print("Email not found. Please <a href='register.jsp'>register</a> first");
        }
    }
}