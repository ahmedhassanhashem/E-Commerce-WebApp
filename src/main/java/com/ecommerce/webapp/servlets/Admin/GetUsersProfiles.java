package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getUsers")
public class GetUsersProfiles extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            String idParam = request.getParameter("id");
            String emailParam = request.getParameter("email");
            
            List<User> filteredUsers = new ArrayList<>();
            
            if ((idParam == null || idParam.isEmpty()) && (emailParam == null || emailParam.isEmpty())) {
                filteredUsers = userDAO.findAll();
            } 
            // If ID is provided, search by ID
            else if (idParam != null && !idParam.isEmpty()) {
                try {
                    int userId = Integer.parseInt(idParam);
                    User user = userDAO.findById(userId);
                    if (user != null) {
                        filteredUsers.add(user);
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid ID format
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("success", false);
                    errorResponse.addProperty("message", "Invalid user ID format");
                    out.print(errorResponse.toString());
                    return;
                }
            } 
            // If email is provided, search by email
            else if (emailParam != null && !emailParam.isEmpty()) {
                User user = userDAO.findByEmail(emailParam);
                if (user != null) {
                    filteredUsers.add(user);
                }
            }
            
            List<JsonObject> userDtos = new ArrayList<>();
            for (User user : filteredUsers) {
                JsonObject userDto = new JsonObject();
                userDto.addProperty("userId", user.getUserId());
                userDto.addProperty("name", user.getName());
                userDto.addProperty("email", user.getEmail());
                userDto.addProperty("phone", user.getPhone());
                userDto.addProperty("address", user.getAddress());
                userDto.addProperty("creditBalance", user.getCreditBalance());
                userDtos.add(userDto);
            }
            
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", true);
            jsonResponse.add("users", gson.toJsonTree(userDtos));
            
            out.print(jsonResponse.toString());
            
        } catch (Exception e) {
            e.printStackTrace(); 
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("success", false);
            errorResponse.addProperty("message", "Error loading users: " + e.getMessage());
            out.print(errorResponse.toString());
        }
    }
}