package com.springbootreactjsjwtauth.controller.rest;

import com.springbootreactjsjwtauth.entity.common.Users;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.entity.response.UserResponse;
import com.springbootreactjsjwtauth.jwt.JWTAuth;
import com.springbootreactjsjwtauth.service.TokenService;
import com.springbootreactjsjwtauth.service.UserService;
import com.springbootreactjsjwtauth.utils.GlobalSuccessResponse;
import com.springbootreactjsjwtauth.utils.LinkConfig;
import com.springbootreactjsjwtauth.utils.email.EmailSenderService;
import com.springbootreactjsjwtauth.utils.email.EmailTemplate;
import com.springbootreactjsjwtauth.utils.email.OtpService;
import com.springbootreactjsjwtauth.utils.exceptions.UserNotActivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.springbootreactjsjwtauth.utils.LinkConfig.*;

@RestController
@RequestMapping(value = LinkConfig.BASE_URL)
public class RegisterUserRestController {

    @Autowired
    public OtpService otpService;
    @Autowired
    public EmailSenderService emailSenderService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    private GlobalSuccessResponse globalSuccessResponse;

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<SuccessResponse> register(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String password,
                                                    @RequestParam String reenter_password, @RequestParam(required = false) Integer type) {
        userService.checkEmptyParameter(first_name, last_name, email, password, reenter_password);

        userService.checkPassword(password, reenter_password);

        userService.checkRegistration(email);

        userService.save(first_name, last_name, email, password, false, type);

        generateOtp(email);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_USER_REGISTERED, HttpStatus.CREATED);
    }

    @GetMapping(value = "/validatelogin")
    @ResponseBody
    public ResponseEntity<UserResponse> login(@RequestHeader(value = "Authorization") String authorization) {
        Users user = userService.getAuthorization(authorization);

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.userResponseHandler(true, SUCCESS_USER_VALIDATED, HttpStatus.OK, user);
    }

    @PostMapping(value = "/validateOtp")
    @ResponseBody
    public ResponseEntity<UserResponse> validateOtp(@RequestParam("email") String email, @RequestParam("otp_code") int otp_code) {
        Users user = userService.findByEmail(email);

        if (!checkOTPCode(user, otp_code)) {
            throw new UserNotActivatedException(ERROR_USER_INVALID_OTP);
        }

        otpService.clearOTP(user.getEmail());

        JWTAuth jwtAuth = new JWTAuth();
        String token = jwtAuth.getJWT(user.getEmail());
        // response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        user.setEnabled(true);
        user.setToken(token);

        userService.update(user);

        tokenService.update(token, user.getId());

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.userResponseHandler(true, SUCCESS_USER_ACTIVATED, HttpStatus.OK, user);
    }

    @PostMapping(value = "/resendOtp")
    @ResponseBody
    public ResponseEntity<SuccessResponse> resendOtp(@RequestParam("email") String email) {
        Users user = userService.checkUserByEmail(email);

        otpService.clearOTP(user.getEmail());

        generateOtp(user.getEmail());

        globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(true, SUCCESS_USER_LOGIN_OTP, HttpStatus.OK);
    }

    /*@RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            String username = auth.getName();

            //Remove the recently used OTP from server.
            otpService.clearOTP(username);

            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }*/

    private void generateOtp(String email) {
        Users user = userService.findByEmail(email);

        int otp = otpService.generateOTP(user.getEmail());

        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("SendOtp.html");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("user", user.getFirstname() + " " + user.getLastname());
        replacements.put("otp_code", String.valueOf(otp));

        String message = template.getTemplate(replacements);

        emailSenderService.sendMail(email, "OTP to activate your account", message);
    }

    private boolean checkOTPCode(Users user, int otp_code) {
        boolean checkOTP;

        if (otp_code >= 0) {
            int serverOtp = otpService.getOtp(user.getEmail());

            if (serverOtp > 0) {
                checkOTP = otp_code == serverOtp;
            } else {
                checkOTP = false;
            }
        } else {
            checkOTP = false;
        }

        return checkOTP;
    }

}
