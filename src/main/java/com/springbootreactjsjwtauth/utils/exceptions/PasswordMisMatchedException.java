package com.springbootreactjsjwtauth.utils.exceptions;

public class PasswordMisMatchedException extends RuntimeException {

    private static final long serialVersionUID = 3641723692621732176L;

    public PasswordMisMatchedException(String s) {
        super(s);
    }

}
