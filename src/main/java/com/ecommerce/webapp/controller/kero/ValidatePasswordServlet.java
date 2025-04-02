package com.ecommerce.webapp.controller.kero;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.ecommerce.webapp.model.kero.User;
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

        String submittedPassword = request.getParameter("password");
        if (submittedPassword != null && currentUser.getPassword().equals(submittedPassword)) {
            isValid = true;
        }

        // Return JSON response
        out.print("{\"valid\": " + isValid + "}");
        out.flush();
    }
}
