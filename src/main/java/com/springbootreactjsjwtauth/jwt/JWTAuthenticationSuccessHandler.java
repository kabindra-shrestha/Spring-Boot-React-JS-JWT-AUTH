package com.springbootreactjsjwtauth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.entity.jwt.SuccessResponse;
import com.springbootreactjsjwtauth.service.TokenService;
import com.springbootreactjsjwtauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.springbootreactjsjwtauth.utils.LinkConfig.ERROR_USER_NOT_ACTIVATED;
import static com.springbootreactjsjwtauth.utils.LinkConfig.SUCCESS_USER_LOGIN;

@Component
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    public JWTAuthenticationSuccessHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String email = ((User) authentication.getPrincipal()).getUsername();

        Users user = userService.findByEmail(email);

        if (user.getEnabled()) {
            JWTAuth jwtAuth = new JWTAuth();
            String token = jwtAuth.getJWT(((User) authentication.getPrincipal()).getUsername());
            // response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

            user.setToken(token);

            userService.update(user);

            tokenService.update(token, user.getId());

            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), SuccessResponse.of(true, SUCCESS_USER_LOGIN, user));
        } else {
            user = null;

            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            mapper.writeValue(response.getWriter(), SuccessResponse.of(true, ERROR_USER_NOT_ACTIVATED, user));
        }

    }

}
