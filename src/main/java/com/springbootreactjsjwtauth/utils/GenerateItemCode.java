package com.springbootreactjsjwtauth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateItemCode {

    public String getBcryptHashItemCode(String itemCode) {
        BCryptPasswordEncoder itemCodeEncoder = new BCryptPasswordEncoder();
        String hashedItemCode = itemCodeEncoder.encode(itemCode);

        return hashedItemCode;
    }

    public boolean matchBcryptHashItemCode(String rawItemCode, String encodedItemCode) {
        BCryptPasswordEncoder itemCodeEncoder = new BCryptPasswordEncoder();

        return itemCodeEncoder.matches(rawItemCode, encodedItemCode);
    }

}
