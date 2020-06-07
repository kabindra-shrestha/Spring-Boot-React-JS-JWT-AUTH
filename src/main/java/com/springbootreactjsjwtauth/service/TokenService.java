package com.springbootreactjsjwtauth.service;

import com.springbootreactjsjwtauth.entity.common.Token;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.repository.TokenModel;
import com.springbootreactjsjwtauth.repository.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springbootreactjsjwtauth.utils.LinkConfig.ERROR_USER_DOESNT_EXIST;

@Service
public class TokenService {

    @Autowired
    private TokenModel tokenModel;
    @Autowired
    private UserModel userModel;

    public Token checkTokenByUserId(long userId) {
        Users users = userModel.findById(userId);
        Token token = findByUserid(users).get(0);

        if (token == null) {
            throw new UsernameNotFoundException(ERROR_USER_DOESNT_EXIST);
        }

        return token;
    }

    public void save(String token, long userId) {
        Users users = userModel.findById(userId);
        Token tokens = new Token();

        tokens.setAuth(token);
        tokens.setDevice("");
        tokens.setUserid(users);

        tokenModel.save(tokens);
    }

    public void update(String token, long userId) {
        Users users = userModel.findById(userId);
        List<Token> tokenList = findByUserid(users);
        Token tokens;

        if (tokenList.size() > 0) {
            tokens = tokenList.get(0);

            tokens.setAuth(token);
            tokens.setDevice("");
            tokens.setUserid(users);
        } else {
            tokens = new Token();

            tokens.setAuth(token);
            tokens.setDevice("");
            tokens.setUserid(users);
        }

        tokenModel.save(tokens);
    }

    public void deleteById(long id) {
        tokenModel.deleteById(id);
    }

    private List<Token> findByUserid(Users users) {
        return tokenModel.findByUserid(users);
    }

}