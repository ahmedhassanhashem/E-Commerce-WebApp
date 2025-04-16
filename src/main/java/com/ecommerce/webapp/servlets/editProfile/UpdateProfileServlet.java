package com.ecommerce.webapp.servlets.editProfile;

import com.ecommerce.webapp.dao.UserDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.webapp.entities.User;

@WebServlet("/update-profile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        JsonObject jsonResponse = new JsonObject();

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "User is not logged in.");
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        // Extract form fields from the multipart request
        Map<String, String> formData = new HashMap<>();
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getSubmittedFileName() == null) { // Ignore file uploads
                formData.put(part.getName(), request.getParameter(part.getName()));
            }
        }

        String name = formData.get("name");
        String email = formData.get("email");
        String phone = formData.get("phone");
        String address = formData.get("address");

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty()) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "All fields are required!");
        } else {
            // Update user in database first
            UserDAO userDAO = new UserDAO();
            boolean updated = userDAO.updateUserProfile(user.getUserId(), name, email, phone, address);

            if (updated) {
                // Update session after successful DB update
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                session.setAttribute("user", user);

                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Profile updated successfully!");
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Failed to update profile in database.");
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}