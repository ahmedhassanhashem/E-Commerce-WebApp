package com.ecommerce.webapp.filters;

import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*") // Applies to all requests
public class TransactionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            // 1. Get EntityManager from EMF
            em = ((EntityManagerFactory) request.getServletContext()
                    .getAttribute("emf")).createEntityManager();

            // 2. Bind EM to ThreadLocal (for repository access)
            PersistenceManager.setEntityManager(em);

            // 3. Begin transaction
            tx = em.getTransaction();
            tx.begin();

            // 4. Process request
            chain.doFilter(request, response);

            // 5. Commit transaction
            if (tx.isActive() && !tx.getRollbackOnly()) {
                tx.commit();
            }

        } catch (Exception ex) {
            // 6. Rollback on error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new ServletException(ex);
        } finally {
            // 7. Cleanup resources
            PersistenceManager.clearEntityManager();
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}