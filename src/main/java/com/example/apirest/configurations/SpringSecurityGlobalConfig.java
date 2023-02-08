package com.example.apirest.configurations;

import com.example.apirest.configurations.jwt.JwtAuthenticationEntryPoint;
import com.example.apirest.configurations.jwt.JwtFilter;
import com.example.apirest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SpringSecurityGlobalConfig {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JwtFilter jwtTokenFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /* Spring Security uses the Synchronizer Token pattern to generate a CSRF token that
    protects against CSRF attacks. When an HTTP request is submitted, Spring Security will
    compare the expected CSRF token with the one sent in the HTTP request. The request will
    be processed only if the token values match else the request will be treated as a forged
    request and be rejected with status 403 (Forbidden). Avec JWT, plus de problème de CSRF donc
    on les désactive.*/

    /**
     * - Enable Cors, disable csrf
     * - Set session management to stateless
     * - Set unauthorized requests exception handler
     * - Set permissions on endpoints
     * - Add JWT token filter before the Spring Security internal UsernamePasswordAuthenticationFilter because we need
     * access to the user identity at this point to perform authentication/authorization, and its extraction happens inside
     * the JWT token filter based on the provided JWT token
     **/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/recette").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .formLogin();
        return http.build();
    }

    // Used by Spring Security if CORS is enabled. 600l => 600 secondes.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://localhost:8090/api");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(600L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder)
                .and()
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }
}
