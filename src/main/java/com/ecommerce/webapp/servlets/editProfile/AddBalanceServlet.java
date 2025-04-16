package com.ecommerce.webapp.servlets.editProfile;

import com.ecommerce.webapp.dao.UserDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ecommerce.webapp.entities.User;
@WebServlet("/add-balance")
public class AddBalanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonResponse = new JsonObject();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "User not logged in.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        User user = (User) session.getAttribute("user");
        String amountStr = request.getParameter("credit");

        if (amountStr == null || amountStr.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Amount is required.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Amount must be greater than zero.");
            } else {
                double newBalance = user.getCreditBalance() + amount;

                // Update user balance in database
                UserDAO userDAO = new UserDAO();
                boolean updated = userDAO.updateUserBalance(user.getUserId(), newBalance);

                if (updated) {
                    // Update session only after successful DB update
                    user.setCreditBalance(newBalance);
                    session.setAttribute("user", user);

                    jsonResponse.addProperty("success", true);
                    jsonResponse.addProperty("message", "Balance updated successfully.");
                    jsonResponse.addProperty("newBalance", newBalance);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    jsonResponse.addProperty("success", false);
                    jsonResponse.addProperty("message", "Failed to update balance in database.");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid amount format.");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}