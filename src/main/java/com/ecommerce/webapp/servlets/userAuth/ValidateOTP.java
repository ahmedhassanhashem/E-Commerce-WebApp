package com.ecommerce.webapp.servlets.userAuth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/validateOTP")
public class ValidateOTP extends HttpServlet {
    
    // OTP timeout in milliseconds (15 minutes)
    private static final long OTP_TIMEOUT = 15 * 60 * 1000;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        
        // Check if OTP session exists
        Integer storedOtp = (Integer) session.getAttribute("otp");
        String storedEmail = (String) session.getAttribute("email");
        Long otpTimestamp = (Long) session.getAttribute("otpTimestamp");
        
        if (storedOtp == null || storedEmail == null || otpTimestamp == null) {
            request.setAttribute("errorMessage", "Session expired. Please restart the password reset process.");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Check if OTP is expired
        long currentTime = new Date().getTime();
        if (currentTime - otpTimestamp > OTP_TIMEOUT) {
            session.removeAttribute("otp");
            session.removeAttribute("otpTimestamp");
            request.setAttribute("errorMessage", "Verification code has expired. Please request a new code.");
            dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Validate entered OTP
        String userOtpStr = request.getParameter("otp");
        if (userOtpStr == null || userOtpStr.trim().isEmpty()) {
            request.setAttribute("status", "failed");
            request.setAttribute("errorMessage", "Please enter the verification code");
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        try {
            int userOtp = Integer.parseInt(userOtpStr);
            
            if (userOtp == storedOtp) {
                // OTP is valid, proceed to password reset
                request.setAttribute("email", storedEmail); 
                request.setAttribute("status", "success");
                
                // Mark the OTP as verified in session
                session.setAttribute("otpVerified", true);
                
                dispatcher = request.getRequestDispatcher("newPassword.jsp");
            } else {
                // Invalid OTP
                request.setAttribute("status", "failed");
                request.setAttribute("errorMessage", "Invalid verification code. Please try again.");
                dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            }
            
            dispatcher.forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("status", "failed");
            request.setAttribute("errorMessage", "Invalid verification code format");
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
        }
    }
}