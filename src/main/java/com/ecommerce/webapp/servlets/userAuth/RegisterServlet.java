package com.ecommerce.webapp.servlets.userAuth;


import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Parse credit limit
        double creditLimit = 0.0;
        try {
            creditLimit = Double.parseDouble(request.getParameter("credit"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid credit limit format");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Validate password match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Create user object
        User user = new User();
        Cart cart = new Cart();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(password);
        user.setCreditBalance(creditLimit);
        user.setCart(cart);
        // Register user
        UserDAO userDAO = new UserDAO();
        boolean isRegistered = userDAO.registerUser(user);
        
        if (isRegistered) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("successMessage", "Registration successful!");
            
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Registration failed
            request.setAttribute("errorMessage", "Registration failed. Email or phone may already be in use.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If someone navigates to /register via GET, redirect to the registration page
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}