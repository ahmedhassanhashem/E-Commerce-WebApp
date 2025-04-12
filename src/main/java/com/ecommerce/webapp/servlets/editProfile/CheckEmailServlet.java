package com.ecommerce.webapp.servlets.editProfile;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/check-email")
public class CheckEmailServlet extends HttpServlet {
    //    Dummy emails
    private static final List<String> EXISTING_EMAILS = Arrays.asList("test@example.com", "user@example.com", "admin@example.com");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        //hibernate part to get all the emails without getting a list of users

//        Session HibernateUtil = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
//        query.setParameter("email", email);
//        boolean exists = !query.getResultList().isEmpty();
//        session.close();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        boolean exists = EXISTING_EMAILS.contains(email);

        // Return JSON response
        out.print("{\"exists\": " + exists + "}");
        out.flush();
    }
}


