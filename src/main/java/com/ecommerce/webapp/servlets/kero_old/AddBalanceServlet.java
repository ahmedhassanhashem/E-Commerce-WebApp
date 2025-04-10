//package com.ecommerce.webapp.controller.kero_old;
//
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//import com.ecommerce.webapp.model.kero.User;
//
//@WebServlet("/add-balance")
//public class AddBalanceServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("currentUser");
//        String amountStr = request.getParameter("amount");
//
//        try {
//            double amount = Double.parseDouble(amountStr);
//            double currentBalance = user.getBalance();
//            user.setBalance(currentBalance + amount);
//            session.setAttribute("currentUser", user);
//
//            response.setContentType("text/plain");
//            response.getWriter().write("Balance updated successfully!");
//        } catch (Exception e) {
//            response.getWriter().write("Invalid amount.");
//        }
//    }
//}
//
//
