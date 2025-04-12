package com.ecommerce.webapp.servlets;

import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validate-password")
public class ValidatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        boolean isValid = false;

        if (session == null) {
            out.print("{\"valid\": false, \"error\": \"Session expired\"}");
            out.flush();
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            out.print("{\"valid\": false, \"error\": \"No user logged in\"}");
            out.flush();
            return;
        }

        // Get the current password from the request parameter
        String currentPassword = request.getParameter("currentPassword");

        if (currentPassword == null) {
            out.print("{\"valid\": false, \"error\": \"No password provided\"}");
            out.flush();
            return;
        }

        // Validate if the submitted password matches the user's password with exact string comparison
        if (currentUser.getPassword().equals(currentPassword)) {
            isValid = true;
        }

        // Return JSON response
        out.print("{\"valid\": " + isValid + "}");
        out.flush();
    }
}