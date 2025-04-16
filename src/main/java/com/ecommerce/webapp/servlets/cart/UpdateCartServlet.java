package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.dto.CartDTO;
import com.ecommerce.webapp.dto.Mapper;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update-cart-item")
public class UpdateCartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
            return;
        }

        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            boolean success = cartDAO.updateItemQuantity(cartItemId, quantity);
            if (!success) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to update item quantity");
                return;
            }

            Cart cart = cartDAO.getCartByUser(user);
            session.setAttribute("cart", cart);
            CartDTO cartDTO = Mapper.mapToDTO(cart);

            em.getTransaction().commit();

            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(cartDTO));
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException(e);
        }
    }
}