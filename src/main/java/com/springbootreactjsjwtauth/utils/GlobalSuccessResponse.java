package com.springbootreactjsjwtauth.utils;

import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.entity.response.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalSuccessResponse {

    public ResponseEntity<SuccessResponse> successResponseHandler(boolean successStatus, String successMessage, HttpStatus responseStatus) {
        SuccessResponse json = new SuccessResponse();

        HttpHeaders headers = new HttpHeaders();

        json.setStatus(successStatus);
        json.setMessage(successMessage);

        headers.add("message", successMessage);

        return (new ResponseEntity<>(json, headers, responseStatus));
    }

    public ResponseEntity<UserResponse> userResponseHandler(boolean successStatus, String successMessage, HttpStatus responseStatus, Users user) {
        UserResponse json = new UserResponse();

        HttpHeaders headers = new HttpHeaders();

        json.setStatus(successStatus);
        json.setMessage(successMessage);
        json.setUsers(user);

        headers.add("message", successMessage);

        return (new ResponseEntity<>(json, headers, responseStatus));
    }

}


