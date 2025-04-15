package com.ecommerce.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.jsp")
public class JSPAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Check if the request is for my-account.jsp
        if (requestURI.endsWith("/my-account.jsp")) {
            // Redirect to the servlet instead
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/my-account");
            return;
        }

        // Continue with other JSPs normally
        chain.doFilter(request, response);
    }
}