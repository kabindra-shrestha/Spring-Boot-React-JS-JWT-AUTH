package com.springbootreactjsjwtauth.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMatcher {

    public boolean matchEmail(String email) {
        Pattern pattern = Pattern.compile(LinkConfig.REGEX_EMAIL);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
