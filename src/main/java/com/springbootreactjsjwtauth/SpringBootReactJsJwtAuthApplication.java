package com.springbootreactjsjwtauth;

import com.springbootreactjsjwtauth.entity.common.Role;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.repository.RoleModel;
import com.springbootreactjsjwtauth.repository.UserModel;
import com.springbootreactjsjwtauth.utils.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.UUID;

@SpringBootApplication
public class SpringBootReactJsJwtAuthApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReactJsJwtAuthApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootReactJsJwtAuthApplication.class);
    }

    private GeneratePassword generatePassword = new GeneratePassword();

    @Autowired
    private UserModel userModel;
    @Autowired
    private RoleModel roleModel;

    private Role findRoleById(long id) {
        return roleModel.findById(id);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role();
        role1.setId(1);
        role1.setType("ROLE_ADMIN");
        roleModel.save(role1);

        Role role2 = new Role();
        role2.setId(2);
        role2.setType("ROLE_USER");
        roleModel.save(role2);

        String activationToken = UUID.randomUUID().toString();

        Users users = new Users();

        users.setFirstname("Kabindra");
        users.setLastname("Shrestha");
        users.setEmail("kabindra@shrestha.com");
        users.setPassword(generatePassword.getBcryptHashPassword("shrestha"));
        users.setEnabled(true);
        users.setActivationToken(activationToken);
        users.setExpiryDate(users.calculateExpiryDate(Users.EXPIRATION));

        users.setRoleid(findRoleById(2)); // default role is user = 2

        userModel.save(users);
    }
}
