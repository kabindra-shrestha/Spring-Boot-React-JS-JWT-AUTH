package com.springbootreactjsjwtauth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.springbootreactjsjwtauth.jwt.SecurityConstants.*;

// For checking JWT Authentication at the time of hitting other apis
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        // System.err.println("JWTAuthorizationFilter: doFilterInternal first");

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            // System.err.println("JWTAuthorizationFilter: doFilterInternal second");

            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        // System.err.println("JWTAuthorizationFilter: doFilterInternal seventh");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // System.err.println("JWTAuthorizationFilter: doFilterInternal third");

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // System.err.println("JWTAuthorizationFilter: doFilterInternal fourth");

            try {
                // parse the token.
                String user = Jwts.parser().setSigningKey(SECRET.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();

                // System.err.println("JWTAuthorizationFilter: doFilterInternal fifth");

                if (user != null) {

                    // System.err.println("JWTAuthorizationFilter: doFilterInternal sixth");
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            }

            return null;
        }
        return null;
    }

}
