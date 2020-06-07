package com.springbootreactjsjwtauth.utils.exceptions;

public class EmailMisMatchedException extends RuntimeException {

    private static final long serialVersionUID = 3641723692621732176L;

    public EmailMisMatchedException(String s) {
        super(s);
    }

}
