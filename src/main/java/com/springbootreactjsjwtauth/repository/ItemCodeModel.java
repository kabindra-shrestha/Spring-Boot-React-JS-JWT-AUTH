package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.ItemCode;
import com.springbootreactjsjwtauth.entity.common.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCodeModel extends JpaRepository<ItemCode, Long> {

    ItemCode findById(long id);

    List<ItemCode> findByUserid(Users userid);

}
