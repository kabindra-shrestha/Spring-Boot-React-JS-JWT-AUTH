package com.springbootreactjsjwtauth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootreactjsjwtauth.entity.common.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//For checking JWT Authentication at the time of login
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTAuthenticationSuccessHandler successHandler;
    private JWTAuthenticationFailureHandler failureHandler;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTAuthenticationSuccessHandler successHandler, JWTAuthenticationFailureHandler failureHandler) {
        this.authenticationManager = authenticationManager;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            // System.err.println("JWTAuthenticationFilter: attemptAuthentication first");

            Users creds = new ObjectMapper().readValue(req.getInputStream(), Users.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
                    creds.getPassword(), new ArrayList<>()));
        } catch (Exception e) {
            // System.err.println("JWTAuthenticationFilter: attemptAuthentication second" +
            // e.getMessage());

            if (e.getClass() == InternalAuthenticationServiceException.class) {
                throw new InternalAuthenticationServiceException(e.getMessage());
            } else if (e.getClass() == BadCredentialsException.class) {
                throw new BadCredentialsException(e.getMessage());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, authentication);
    }

}