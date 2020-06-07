package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.Token;
import com.springbootreactjsjwtauth.entity.common.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TokenModel extends JpaRepository<Token, Long> {

    Token findById(long id);

    List<Token> findByUserid(Users user);

}
