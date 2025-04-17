package com.ecommerce.webapp.servlets.userAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the referer (the page user was on before logout)
        String referer = request.getHeader("Referer");
        String returnUrl = (referer != null) ? referer : "home";

        // Get session and invalidate it
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Delete remember-me cookie if exists
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMeToken".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Delete cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        // Redirect to login page with the return URL as a parameter
        response.sendRedirect("login.jsp?returnUrl=" + response.encodeRedirectURL(returnUrl));
    }
}