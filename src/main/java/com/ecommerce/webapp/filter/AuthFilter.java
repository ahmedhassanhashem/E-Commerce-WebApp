package com.ecommerce.webapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class AuthFilter implements Filter {
    
    // Same token storage as in LoginServlet 
    private static final Map<String, String> REMEMBER_ME_TOKENS = new HashMap<>();
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // Check if user is already logged in
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        // If not logged in, try auto-login with remember-me cookie
        if (!isLoggedIn) {
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("rememberMeToken".equals(cookie.getName())) {
                        String token = cookie.getValue();
                        String email = REMEMBER_ME_TOKENS.get(token);
                        
                        if (email != null) {
                            // Valid token found, create session
                            session = httpRequest.getSession(true);
                            
                            // Create user data (same as in LoginServlet)
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("email", email);
                            userData.put("name", email.split("@")[0]);
                            
                            // Check if admin
                            boolean isAdmin = "admin@gmail.com".equals(email);
                            
                            // Set session attributes
                            session.setAttribute("user", userData);
                            session.setAttribute("isAdmin", isAdmin);
                            
                            // User is now logged in
                            isLoggedIn = true;
                            break;
                        }
                    }
                }
            }
        }
        
        // Check if trying to access admin area
        String requestPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        boolean isAdminPage = requestPath.startsWith("/admin/");
        
        if (isAdminPage) {
            // Check if user is logged in and is admin
            boolean isAdmin = (session != null && session.getAttribute("isAdmin") != null && 
                              (Boolean) session.getAttribute("isAdmin"));
                              
            if (!isAdmin) {
                // Not authorized to access admin area
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                return;
            }
        }
        
        // Continue with request
        chain.doFilter(request, response);
    }

}