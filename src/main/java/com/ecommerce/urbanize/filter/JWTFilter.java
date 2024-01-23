package com.ecommerce.urbanize.filter;

import org.springframework.stereotype.Component;

import com.ecommerce.urbanize.helper.JWTHelper;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWTFilter class responsible for filtering incoming requests and handling
 * JWT-based authentication.
 */
@Component
public class JWTFilter implements Filter {

    /**
     * Initializes the filter. (Empty method implementation)
     * 
     * @param filterConfig The filter configuration.
     * @throws ServletException If an error occurs during initialization.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filters incoming requests to handle JWT-based authentication.
     * 
     * @param servletRequest  The servlet request.
     * @param servletResponse The servlet response.
     * @param filterChain     The filter chain.
     * @throws IOException      If an I/O error occurs.
     * @throws ServletException If an error occurs during filtering.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Handle pre-flight OPTIONS requests
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Extract and validate the JWT token from the Authorization header
            String auth = request.getHeader("Authorization");
            if (auth != null && auth.startsWith("Bearer ")) {
                String token = auth.substring(7);
                String username = JWTHelper.validateJWT(token);

                // If the token is valid, set the username attribute in the request
                if (username != null) {
                    request.setAttribute("username", username);
                }
            }
        }

        // Continue the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // Destroys the filter. (Empty method implementation)
    @Override
    public void destroy() {
    }
}