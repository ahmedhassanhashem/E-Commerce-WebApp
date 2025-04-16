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
        "admin@gmail.com"
    );
    
    private static final Map<String, String> REMEMBER_ME_TOKENS = new HashMap<>();
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Capture the returnUrl if it exists
        String returnUrl = request.getParameter("returnUrl");
        if (returnUrl != null && !returnUrl.isEmpty()) {
            request.setAttribute("returnUrl", returnUrl);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember-me");
        String returnUrl = request.getParameter("returnUrl");

        if (userDAO.validate(email, password)) {

            User user = userDAO.findByEmail(email);

            // Check if user is admin based on static email list
            boolean isAdmin = ADMIN_EMAILS.contains(email);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
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

            // Determine where to redirect the user
            if (isAdmin) {
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                // Check for last visited URL in the session
                String lastVisitedUrl = (String) session.getAttribute("lastVisitedUrl");

                // Use the explicit returnUrl parameter if provided
                if (returnUrl != null && !returnUrl.isEmpty() && !returnUrl.contains("login.jsp")) {
                    response.sendRedirect(returnUrl);
                }
                // Otherwise use the last visited URL from session if available
                else if (lastVisitedUrl != null && !lastVisitedUrl.isEmpty() &&
                        !lastVisitedUrl.contains("login.jsp") && !lastVisitedUrl.contains("logout")) {
                    response.sendRedirect(lastVisitedUrl);
                }
                // Default fallback
                else {
                    response.sendRedirect("home");
                }
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");

            // Preserve returnUrl in case of login failure
            if (returnUrl != null && !returnUrl.isEmpty()) {
                request.setAttribute("returnUrl", returnUrl);
            }

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