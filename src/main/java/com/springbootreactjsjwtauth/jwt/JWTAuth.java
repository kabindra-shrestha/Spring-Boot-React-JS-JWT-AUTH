package com.springbootreactjsjwtauth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import static com.springbootreactjsjwtauth.jwt.SecurityConstants.EXPIRATION_TIME;
import static com.springbootreactjsjwtauth.jwt.SecurityConstants.SECRET;

public class JWTAuth {

    public String getJWT(String payload) {
        return Jwts.builder().setSubject(payload)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
    }

}
