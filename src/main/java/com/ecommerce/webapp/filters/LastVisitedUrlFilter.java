package com.ecommerce.webapp.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class LastVisitedUrlFilter implements Filter {

    private static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/login", "/logout", "/register", "/forgot-password",
            "/js/", "/css/", "/images/", "/fonts/", "/favicon");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String urlPath = requestURI.substring(contextPath.length());

        // Only store URLs for GET requests to actual pages (not resources or login/logout pages)
        if ("GET".equals(httpRequest.getMethod()) && !isExcludedUrl(urlPath)) {
            HttpSession session = httpRequest.getSession();

            // Don't save if user isn't authenticated yet (except for public pages)
            if (session.getAttribute("user") != null || isPublicPage(urlPath)) {
                String fullURL = requestURI;
                String queryString = httpRequest.getQueryString();
                if (queryString != null) {
                    fullURL += "?" + queryString;
                }

                session.setAttribute("lastVisitedUrl", fullURL);
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isExcludedUrl(String urlPath) {
        return EXCLUDED_URLS.stream().anyMatch(urlPath::startsWith);
    }

    private boolean isPublicPage(String urlPath) {
        // Define your public pages that don't require login
        List<String> publicPages = Arrays.asList("/home", "/product", "/category", "/about", "/contact");
        return publicPages.stream().anyMatch(urlPath::startsWith) || urlPath.equals("/");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}