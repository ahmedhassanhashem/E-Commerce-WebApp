package com.ecommerce.webapp.servlets.editProfile;


import com.ecommerce.webapp.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
@WebServlet("/check-email")
public class CheckEmailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        // Use UserDAO to check if email exists
        UserDAO userDAO = new UserDAO();
        boolean exists = userDAO.emailExists(email);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Return JSON response
        out.print("{\"exists\": " + exists + "}");
        out.flush();
    }
}