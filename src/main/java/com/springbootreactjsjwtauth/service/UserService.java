package com.springbootreactjsjwtauth.service;

import com.springbootreactjsjwtauth.entity.common.Role;
import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.repository.RoleModel;
import com.springbootreactjsjwtauth.repository.UserModel;
import com.springbootreactjsjwtauth.utils.EmailMatcher;
import com.springbootreactjsjwtauth.utils.GeneratePassword;
import com.springbootreactjsjwtauth.utils.PasswordMatcher;
import com.springbootreactjsjwtauth.utils.exceptions.EmailMisMatchedException;
import com.springbootreactjsjwtauth.utils.exceptions.EmptyParameterException;
import com.springbootreactjsjwtauth.utils.exceptions.PasswordMisMatchedException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.springbootreactjsjwtauth.jwt.SecurityConstants.SECRET;
import static com.springbootreactjsjwtauth.jwt.SecurityConstants.TOKEN_PREFIX;
import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@Service
public class UserService {

    private GeneratePassword generatePassword = new GeneratePassword();

    @Autowired
    private UserModel userModel;
    @Autowired
    private RoleModel roleModel;

    public Users getAuthorization(String authorization) {
        String username = Jwts.parser().setSigningKey(SECRET.getBytes())
                .parseClaimsJws(authorization.replace(TOKEN_PREFIX, "")).getBody().getSubject();
        Users users = findByEmail(username);

        if (users == null) {
            throw new UsernameNotFoundException(ERROR_USER_DOESNT_EXIST);
        }

        return users;
    }

    public Users checkUserByEmail(String email) {
        Users users = findByEmail(email);

        if (users == null) {
            throw new UsernameNotFoundException(ERROR_USER_DOESNT_EXIST);
        }

        return users;
    }

    public Users checkRegistration(String email) {
        Users users = findByEmail(email);

        checkEmail(email);

        if (users != null) {
            throw new DuplicateKeyException(ERROR_USER_EXIST_EMAIL + email);
        }

        return users;
    }

    /*public Users checkRegistrationUsername(String username) throws MySQLIntegrityConstraintViolationException {
        Users users = findByUsername(username);

        if (users != null) {
            throw new MySQLIntegrityConstraintViolationException(ERROR_USER_EXIST_USERNAME + username + ERROR_USER_EXIST_USERNAME_YET);
        }

        return users;
    }*/

    public void checkEmptyParameter(String first_name, String last_name, String email, String password, String reenter_password) {
        if (first_name == null || first_name.equalsIgnoreCase("")) {
            throw new EmptyParameterException("Please provide first_name parameter.");
        }
        if (last_name == null || last_name.equalsIgnoreCase("")) {
            throw new EmptyParameterException("Please provide last_name parameter.");
        }
        if (email == null || email.equalsIgnoreCase("")) {
            throw new EmptyParameterException("Please provide email parameter.");
        }
        if (password == null || password.equalsIgnoreCase("")) {
            throw new EmptyParameterException("Please provide password parameter.");
        }
        if (reenter_password == null || reenter_password.equalsIgnoreCase("")) {
            throw new EmptyParameterException("Please provide reenter_password parameter.");
        }
    }

    public void checkPassword(String password, String reenter_password) {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        boolean matchPassword = passwordMatcher.matchPassword(password);
        boolean matchConfirmPassword = passwordMatcher.matchPassword(reenter_password);

        if (!password.equals(reenter_password)) {
            throw new PasswordMisMatchedException(ERROR_PASSWORD_MIS_MATCHED);
        }

        if (!matchPassword) {
            throw new PasswordMisMatchedException(ERROR_PASSWORD_INVALID);
        }

        if (!matchConfirmPassword) {
            throw new PasswordMisMatchedException(ERROR_CONFIRM_PASSWORD_INVALID);
        }
    }

    private void checkEmail(String email) {
        EmailMatcher emailMatcher = new EmailMatcher();
        boolean matchEmail = emailMatcher.matchEmail(email);

        if (!matchEmail) {
            throw new EmailMisMatchedException(ERROR_EMAIL_INVALID);
        }
    }

    public void save(String first_name, String last_name, String email, String password, boolean enabled, Integer role) {
        String activationToken = UUID.randomUUID().toString();

        Users users = new Users();

        users.setFirstname(first_name);
        users.setLastname(last_name);
        users.setEmail(email);
        users.setPassword(generatePassword.getBcryptHashPassword(password));
        users.setEnabled(enabled);
        users.setActivationToken(activationToken);
        users.setExpiryDate(users.calculateExpiryDate(Users.EXPIRATION));
        if (role == null) {
            role = 2; // default role is user = 2
        }
        users.setRoleid(findRoleById(role));

        userModel.save(users);
    }

    public void update(Users user) {
        String activationToken = UUID.randomUUID().toString();

        user.setActivationToken(activationToken);
        user.setExpiryDate(user.calculateExpiryDate(Users.EXPIRATION));

        userModel.save(user);
    }

    public void deleteById(long id) {
        userModel.deleteById(id);
    }

    /*public Map<String, Object> findUserInfo(Users user) {
        Map<String, Object> userInfo = new HashMap<String, Object>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.s", Locale.ENGLISH);

        userInfo.put("id", user.getId());
        userInfo.put("firstname", user.getFirstname());
        userInfo.put("lastname", user.getLastname());
        userInfo.put("username", user.getUsername());
        userInfo.put("email", user.getEmail());
        userInfo.put("phone", user.getPhone());
        userInfo.put("mobile", user.getMobile());
        userInfo.put("address", user.getAddress());
        userInfo.put("image", user.getImage());
        userInfo.put("enabled", user.getEnabled());
        userInfo.put("token", user.getToken());
        userInfo.put("createdat", format.format(user.getCreatedat()).substring(0, 19));
        userInfo.put("modifiedat", format.format(user.getModifiedat()).substring(0, 19));
        userInfo.put("role", user.getRoleid().getType());

        return userInfo;
    }*/

    public String findActivationByEmail(String email) {
        return userModel.findByEmail(email).getActivationToken();
    }

    public Users findActivationByToken(String activationToken) {
        return userModel.findByActivationToken(activationToken);
    }

    public Users findById(long id) {
        return userModel.findById(id);
    }

    public Users findByEmail(String email) {
        return userModel.findByEmail(email);
    }

    private Users findByUsername(String username) {
        return userModel.findByUsername(username);
    }

    private Role findRoleById(long id) {
        return roleModel.findById(id);
    }

    public boolean matchPassword(String username, String password) {
        return generatePassword.matchBcryptHashPassword(password, userModel.findByEmail(username).getPassword());
    }

}
