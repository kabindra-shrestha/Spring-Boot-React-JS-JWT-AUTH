package com.springbootreactjsjwtauth.repository;

import com.springbootreactjsjwtauth.entity.common.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleModel extends JpaRepository<Role, Long> {

    Role findById(long id);

    Role findByType(String type);

}
