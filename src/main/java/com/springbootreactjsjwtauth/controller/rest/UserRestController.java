package com.springbootreactjsjwtauth.controller.rest;

import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.entity.response.UserResponse;
import com.springbootreactjsjwtauth.service.UserService;
import com.springbootreactjsjwtauth.utils.GlobalSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@RestController
@RequestMapping(value = BASE_URL + USER_URL)
public class UserRestController {

    @Autowired
    private UserService userService;

    private GlobalSuccessResponse globalSuccessResponse;

    @GetMapping(value = "/info")
    @ResponseBody
    public ResponseEntity<UserResponse> user(@RequestHeader(value = "Authorization") String authorization) {
        Users user = userService.getAuthorization(authorization);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.userResponseHandler(true, SUCCESS_USER_FETCHED, HttpStatus.OK, user);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<SuccessResponse> update(@RequestHeader(value = "Authorization") String authorization, @RequestParam String firstname,
                                                  @RequestParam String lastname, @RequestParam String phone, @RequestParam String mobile, @RequestParam String address) {
        Users user = userService.getAuthorization(authorization);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setMobile(mobile);
        user.setAddress(address);
        userService.update(user);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_USER_UPDATED, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<SuccessResponse> delete(@RequestHeader(value = "Authorization") String authorization) {
        Users user = userService.getAuthorization(authorization);

        userService.deleteById(user.getId());

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_USER_DELETED, HttpStatus.OK);
    }

}
