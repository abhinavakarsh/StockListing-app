package com.example.AuthenticationService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JWTFilters extends GenericFilterBean {

    // Ensure this matches the secret key used in JwtUtil
    private final String secretKey = "mySuperSecretKey123";  // Use a secure key in production

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("Authorization");

        // Bypass token validation for the authentication endpoint
        if (request.getRequestURI().equals("/api/users/authenticate")) {
            filterChain.doFilter(request, response);  // Proceed with the request
            return;  // Exit the filter
        }

        // Handle CORS preflight requests
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);  // Proceed with the request
        } else {
            // Check for the Authorization header
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
                return;  // Exit the filter
            }

            final String token = authHeader.substring(7);  // Extract token from "Bearer "
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody();

                // Optional: You can set claims in request attributes for further use
                request.setAttribute("claims", claims);
                filterChain.doFilter(request, response);  // Proceed with the request
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token: " + e.getMessage());
            }
        }
    }
}
