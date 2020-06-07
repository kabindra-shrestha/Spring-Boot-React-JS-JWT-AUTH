package com.springbootreactjsjwtauth.entity.response;

import com.springbootreactjsjwtauth.entity.common.Users;

public class UserResponse extends MessageResponse {

    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
