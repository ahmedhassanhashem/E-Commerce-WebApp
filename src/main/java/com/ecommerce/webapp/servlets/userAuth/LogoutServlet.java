package com.ecommerce.webapp.servlets.userAuth;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.User;
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
    private final CartDAO cartDAO = new CartDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get session and invalidate it
        HttpSession session = request.getSession(false);
        if(session != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("user");


            if (user != null && cart != null) {
                new CartDAO().clearCart(cart);
                cart.getItems().forEach(item ->
                        cartDAO.addItemToCart(cart, item.getProduct(), item.getQuantity()));
            }
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
        response.sendRedirect("home");
    }
}