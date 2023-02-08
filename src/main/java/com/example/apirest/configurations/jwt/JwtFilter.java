package com.example.apirest.configurations.jwt;

import com.example.apirest.controllers.UserController;
import com.example.apirest.dtos.UserDto;
import com.example.apirest.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.apirest.configurations.ConfigurationConstantPool.*;

@Component
public final class JwtFilter extends OncePerRequestFilter {

    final static Logger logger = LogManager.getLogger(UserController.class);

    private final static String BLANK_SEPARATION = " ";
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
        String userEmail = null;
        String jwtToken = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)){
            jwtToken = requestTokenHeader.split(BLANK_SEPARATION)[1].trim();
            try{
                userEmail = tokenUtil.getUserEmailFromToken(jwtToken);
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            } catch (Exception e) {
                logger.error("Troubles encountered with the JWT");
            }
        }
        // Once we get the token then we validate it.
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDto userDto = userService.loadUserByUsername(userEmail);
            // If token is valid, we save the authentication in our Spring Security context and let the code proceed to the next filter in our filter chain
            if(tokenUtil.validateToken(jwtToken, userDto)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDto, null, userDto.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /* After setting the Authentication in the context, we specify that the current user is authenticated. So it passes the
                   Spring Security Configurations successfully.*/
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
