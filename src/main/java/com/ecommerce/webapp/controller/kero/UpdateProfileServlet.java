package com.ecommerce.webapp.controller.kero;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Calendar;

import com.ecommerce.webapp.model.kero.User;

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Update user data from request parameters
        currentUser.setFirstName(request.getParameter("firstName"));
        currentUser.setLastName(request.getParameter("lastName"));
        currentUser.setGender(request.getParameter("gender"));
        currentUser.setEmail(request.getParameter("email"));
        currentUser.setPhone(request.getParameter("phone"));
        currentUser.setAddress(request.getParameter("address"));

        // Parse birthdate
        int year = Integer.parseInt(request.getParameter("birthYear"));
        int month = Integer.parseInt(request.getParameter("birthMonth"));
        int day = Integer.parseInt(request.getParameter("birthDay"));
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); // Month is 0-based in Calendar
        currentUser.setBirthDate(cal.getTime());

        // Save to database (pseudo-code)
        // userDao.update(currentUser);

        response.sendRedirect("dash-edit-profile.jsp");
    }
}