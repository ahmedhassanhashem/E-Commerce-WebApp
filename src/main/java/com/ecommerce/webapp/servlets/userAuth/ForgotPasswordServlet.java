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
    
  
}