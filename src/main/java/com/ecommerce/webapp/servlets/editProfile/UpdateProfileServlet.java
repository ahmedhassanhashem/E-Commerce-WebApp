package com.ecommerce.webapp.servlets.editProfile;

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
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("success", false);
            errorResponse.addProperty("message", "User is not logged in.");
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
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


        JsonObject jsonResponse = new JsonObject();

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty()) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "All fields are required!");
        } else {
            currentUser.setName(name);
            currentUser.setEmail(email);
            currentUser.setPhone(phone);
            currentUser.setAddress(address);
            session.setAttribute("currentUser", currentUser);


            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Profile updated successfully!");
        }


        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

    }
}