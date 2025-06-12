package com.postgresql.reverbclone.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.postgresql.reverbclone.service.JWTService;
import com.postgresql.reverbclone.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * JwtFilter inherits OncePerRequestFilter
 * An instance of this class is added to the filter chain
 * In the chain it runs once per request to authenticate
 * users via JWT tokens, extracts jwt from Authorization header 
 * validates it, and setups up Spring Security Context
 */
@Component
public class JwtFilter extends OncePerRequestFilter{

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logger.debug("Processing request: {} {}", request.getMethod(), request.getRequestURI());

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        try {
            // if request has header with Authorization key and starts with Bearer
            // that indicates jwt token, set token and username from token
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                logger.debug("JWT token extracted form Authorization header");

                if (!StringUtils.hasText(token)) {
                    logger.warn("Empty JWT token found in Authorization header");
                    filterChain.doFilter(request, response);
                    return;
                }

                username = jwtService.extractUserName(token);

                if (username != null) {
                    logger.debug("Username extracted from JWT: {}", username);
                } else {
                    logger.warn("Could not extract username from JWT token");
                }
            } else {
                logger.debug("No Authorization header or Bearer token found");
            }

            // if username not null but SecurityContextHolder does not find an
            // authentication value it moves into the code block to set it
            // indicating a valid user that has not been applied a jwt token 
            // use myUserDetailsService instantiate userDetails with the username
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.debug("Attempting to authenticate user: {}", username);

                try {
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

                    if (userDetails == null) {
                        logger.error("UserDetails is null for username: {}", username);
                        filterChain.doFilter(request, response);
                        return;
                    }

                    logger.debug("UserDetails loaded for user: {}", username);


                    // use JWTService to validate token using
                    // token and userDetails, set username and password authentication with token
                    if (jwtService.validateToken(token, userDetails)) {
                        logger.debug("JWT token validation successful for user: {}", username);
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        logger.info("User {} succesfully authenticated via JWT", username);
                    } else {
                        logger.warn("JWT token validation failed for user: {}", username);
                    }

                } catch(UsernameNotFoundException e) {
                    logger.error("User not found: {}", username, e);
                } catch(Exception e) {
                    logger.error("Error during user authentication for {}: {}", username, e.getMessage(), e);
                }
            
            } else if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                logger.debug("User {} already authenticated in SecurityContext", username);
            }
        
        } catch(Exception e) {
            logger.error("JWT Filter processing error: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
        
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        boolean shouldSkip = path.equals("/register") || path.equals("/login") ||
                            path.startsWith("/public/") || path.startsWith("/health");

        if(shouldSkip) {
            logger.debug("Skipping JWT filter for public endpoint: {}", path);
        }
        return shouldSkip;
    }
}

