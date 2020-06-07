package com.springbootreactjsjwtauth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootreactjsjwtauth.entity.jwt.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Autowired
    public JWTAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if (exception.getClass() == InternalAuthenticationServiceException.class) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), ErrorResponse.of(false, ERROR_USER_INVALID));
        } else if (exception.getClass() == BadCredentialsException.class) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), ErrorResponse.of(false, ERROR_USER_INVALID_CREDENTIALS));
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), ErrorResponse.of(false, ERROR_USER_NOT_AUTHENTICATED));
        }

        // exception.printStackTrace();
    }

}
