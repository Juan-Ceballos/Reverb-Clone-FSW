package com.postgresql.reverbclone.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        logger.debug("Creating AuthenticationManager bean from AuthenticationConfiguration");
        AuthenticationManager authManager = config.getAuthenticationManager();
        logger.debug("Successfully created AuthenticationManager: {}", authManager.getClass().getSimpleName());
        return authManager;
    }

    // AuthenticationProvider configures how authentication will work
    // Will use BCrypt for password verification and 
    // userDetailsService to load user data
    @Bean
    public AuthenticationProvider authenticationProvider() {
        logger.debug("Adding password encryption to AuthenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Autowired
    private JwtFilter jwtFilter;

    // Filter Chain, permits public endpoints, requires JWT authentication for others
    // due to adding your JWTFilter that extends OncePerRequestFilter
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain with JWT authentication");
        logger.debug("Permitted endpoints: /register, /login");
        logger.debug("Session management: STATELESS");
        
        return http
            .csrf(customizer -> customizer.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(request -> request
            .requestMatchers("/register", "/login")
            .permitAll()
            .anyRequest().authenticated())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
