package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserModel extends JpaRepository<Users, Long> {

    Users findById(long id);

    Users findByEmail(String email);

    Users findByUsername(String username);

    Users findByActivationToken(String activtionToken);

}
