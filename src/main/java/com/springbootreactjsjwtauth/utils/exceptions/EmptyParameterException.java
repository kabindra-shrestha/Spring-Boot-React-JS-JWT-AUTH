package com.springbootreactjsjwtauth.utils.exceptions;

public class EmptyParameterException extends RuntimeException {

    private static final long serialVersionUID = 3641723692621732176L;

    public EmptyParameterException(String s) {
        super(s);
    }

}
