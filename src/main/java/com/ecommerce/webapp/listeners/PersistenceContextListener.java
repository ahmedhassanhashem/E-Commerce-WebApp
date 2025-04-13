package com.ecommerce.webapp.listeners;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.SQLException;

@WebListener
public class PersistenceContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webapp");
        sce.getServletContext().setAttribute("emf", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
        if (emf != null) {
            emf.close();
        }

        // Add these lines to cleanup MySQL threads
        try {
            java.sql.DriverManager.deregisterDriver(
                    java.sql.DriverManager.getDriver("jdbc:mysql://localhost:3306/webapp")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Shutdown MySQL AbandonedConnectionCleanupThread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
    }
}