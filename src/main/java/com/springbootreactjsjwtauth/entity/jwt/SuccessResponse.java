package com.springbootreactjsjwtauth.entity.jwt;

import com.springbootreactjsjwtauth.entity.common.Users;

public class SuccessResponse {

    private final boolean status;
    private final String message;
    private final Users users;

    protected SuccessResponse(final boolean status, final String message, final Users users) {
        this.status = status;
        this.message = message;
        this.users = users;
    }

    public static SuccessResponse of(final boolean status, final String message, final Users users) {
        return new SuccessResponse(status, message, users);
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Users getUsers() {
        return users;
    }

}
