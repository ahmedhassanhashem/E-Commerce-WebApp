package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.UserDAO;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Date;

@WebServlet("/forgotPassword")
public class ResetPassword extends HttpServlet {
    
    // Load mail configuration from context parameters
    private String mailHost;
    private String mailUsername;
    private String mailPassword;
    
    @Override
    public void init() {
        // These values should be set in web.xml as context parameters
        mailHost = getServletContext().getInitParameter("mail.smtp.host");
        mailUsername = getServletContext().getInitParameter("mail.user");
        mailPassword = getServletContext().getInitParameter("mail.password");
        
        // Use defaults if not configured
        if (mailHost == null) mailHost = "smtp.gmail.com";
        if (mailUsername == null) mailUsername = "poshspareparts@gmail.com";
        if (mailPassword == null) mailPassword = "aykcdlzamsatwcab"; // This should be moved to a secure config
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show the form
        RequestDispatcher dispatcher = request.getRequestDispatcher("forgot-password.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("reset-email"); 
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        // Validate email format
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email is required");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Check if email exists in database
        UserDAO userDAO = new UserDAO();
        boolean emailExists = userDAO.findByEmail(email).getEmail() != null;
        
        if (!emailExists) {
            request.setAttribute("errorMessage", "Email not found in our records");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        try {
            // Generate a random 6-digit OTP
            int otp = 100000 + new Random().nextInt(900000);
            
            // Set up mail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", mailHost);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.trust", mailHost);
            
            // Create mail session
            Session mailSession = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailUsername, mailPassword);
                }
            });
            
            // Create and send email
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(mailUsername));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Password Reset Verification Code");
            message.setText("Your verification code for password reset is: " + otp + 
                           "\n\nThis code will expire in 15 minutes.\n\nIf you did not request this reset, please ignore this email.");
            
            Transport.send(message);
            
            // Store OTP and email in session with timestamp
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("otpTimestamp", new Date().getTime());
            session.setAttribute("message", "A verification code has been sent to your email");
            
            // Forward to OTP verification page
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
            
        } catch (MessagingException e) {
            request.setAttribute("errorMessage", "Failed to send verification email. Please try again later.");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
        }
    }
}