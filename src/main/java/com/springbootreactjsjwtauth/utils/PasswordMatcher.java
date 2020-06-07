package com.springbootreactjsjwtauth.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordMatcher {

    public boolean matchPassword(String password) {
        Pattern pattern = Pattern.compile(LinkConfig.REGEX_PASSWORD);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
