package com.springbootreactjsjwtauth.utils.exceptions;

public class UserNotActivatedException extends RuntimeException {

    private static final long serialVersionUID = 3641723692621732176L;

    public UserNotActivatedException(String s) {
        super(s);
    }

}
