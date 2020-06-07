package com.springbootreactjsjwtauth.utils;

public class LinkConfig {

    public static final String BASE_URL = "/api";
    public static final String USER_URL = "/user";
    public static final String ITEM_URL = "/item";
    public static final String ITEM_CODE_URL = "/itemCode";

    public static final String MAIL_SENDER = "app.tech.0wn@gmail.com";

    public static final String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String REGEX_PASSWORD = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public static final String SUCCESS_USER_REGISTERED = "User successfully Registered. Please activate your account through your email.";
    public static final String SUCCESS_USER_LOGIN = "User logged in successful.";
    public static final String SUCCESS_USER_LOGIN_OTP = "We've sent you OTP code successfully. Please activate your account through your email. ";
    public static final String SUCCESS_USER_ACTIVATED = "Your account is activated. Please try logging into the system.";
    public static final String SUCCESS_USER_VALIDATED = "User successfully validated.";
    public static final String SUCCESS_USER_FETCHED = "User Fetched Successfully.";
    public static final String SUCCESS_USER_UPDATED = "User Updated Successfully.";
    public static final String SUCCESS_USER_DELETED = "User Deleted Successfully.";
    public static final String SUCCESS_ITEM_ADDED = "Item successfully added.";
    public static final String SUCCESS_ITEM_FETCHED = "Item Fetched Successfully.";
    public static final String SUCCESS_ITEM_UPDATED = "Item Updated Successfully.";
    public static final String SUCCESS_ITEM_DELETED = "Item Deleted Successfully.";
    public static final String SUCCESS_ITEM_CODE_ADDED = "Item Code successfully added.";
    public static final String SUCCESS_ITEM_CODE_FETCHED = "Item Code Fetched Successfully.";
    public static final String SUCCESS_ITEM_CODE_UPDATED = "Item Code Updated Successfully.";
    public static final String SUCCESS_ITEM_CODE_DELETED = "Item Code Deleted Successfully.";

    public static final String ERROR_USER_NOT_ACTIVATED = "Please activate account through your email.";
    public static final String ERROR_USER_INVALID_OTP = "The activation token has been expired or invalid. Please contact us for further information.";
    public static final String ERROR_USER_INVALID = "The user couldn't be authenticated. Please check your username/email.";
    public static final String ERROR_USER_INVALID_CREDENTIALS = "The user couldn't be authenticated. Please check your password.";
    public static final String ERROR_USER_NOT_AUTHENTICATED = "Authentication failed.";
    public static final String ERROR_USER_DOESNT_EXIST = "User doesn't exists in the system.";
    public static final String ERROR_USER_EXIST_EMAIL = "User exists on database with email ";
    public static final String ERROR_USER_EXIST_USERNAME = "The user already exists with username '";
    public static final String ERROR_USER_EXIST_USERNAME_YET = "' . Please choose another username.";
    public static final String ERROR_EMAIL_INVALID = "Please provide valid email address.";
    public static final String ERROR_PASSWORD_INVALID = "Please provide valid password.";
    public static final String ERROR_CONFIRM_PASSWORD_INVALID = "Please provide valid confirm password.";
    public static final String ERROR_PASSWORD_MIS_MATCHED = "Password doesn't match.";
    public static final String ERROR_ITEM_DOESNT_EXIST = "Item doesn't exists.";
    public static final String ERROR_ITEM_ALREADY_EXIST = "Items already exists with ";
    public static final String ERROR_ITEM_NOT_ADDED = "You haven't added any item yet.";
    public static final String ERROR_LOT_DOESNT_EXIST = "Lot doesn't exists.";
    public static final String ERROR_ITEM_CODE_DOESNT_EXIST = "Item code doesn't exists.";
    public static final String ERROR_ITEM_CODE_ALREADY_EXIST = "Items code already exists with ";
    public static final String ERROR_ITEM_CODE_NOT_ADDED = "You haven't added any item code yet.";

}
