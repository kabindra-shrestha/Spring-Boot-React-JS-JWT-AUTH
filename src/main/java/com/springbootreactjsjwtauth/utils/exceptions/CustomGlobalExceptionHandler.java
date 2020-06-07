package com.springbootreactjsjwtauth.utils.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.springbootreactjsjwtauth.entity.response.SuccessResponse;
import com.springbootreactjsjwtauth.utils.GlobalSuccessResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class CustomGlobalExceptionHandler/* extends ResponseEntityExceptionHandler */ {

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<SuccessResponse> onInternalAuthenticationServiceException(
            final InternalAuthenticationServiceException e) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    public ResponseEntity<SuccessResponse> onMySQLIntegrityConstraintViolationException(
            final MySQLIntegrityConstraintViolationException e) {
        return error(e, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<SuccessResponse> onDuplicateKeyException(final DuplicateKeyException e) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<SuccessResponse> UsernameNotFoundException(final UsernameNotFoundException e) {
        return error(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<SuccessResponse> onBadCredentialsException(final BadCredentialsException e) {
        return error(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<SuccessResponse> onServletRequestBindingException(final ServletRequestBindingException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<SuccessResponse> onExpiredJwtException(final ExpiredJwtException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<SuccessResponse> onMalformedJwtException(final MalformedJwtException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<SuccessResponse> onJsonParseException(final JsonParseException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<SuccessResponse> onSignatureException(final SignatureException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<SuccessResponse> onNotFoundException(final NotFoundException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<SuccessResponse> onIllegalStateException(final IllegalStateException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<SuccessResponse> onExceptions(final InternalAuthenticationServiceException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<SuccessResponse> onContentNotFoundException(final ContentNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActivatedException.class)
    public final ResponseEntity<SuccessResponse> onUserNotActivatedException(final UserNotActivatedException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmailMisMatchedException.class)
    public final ResponseEntity<SuccessResponse> onEmailMisMatchedException(final EmailMisMatchedException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PasswordMisMatchedException.class)
    public final ResponseEntity<SuccessResponse> onPasswordMisMatchedException(final PasswordMisMatchedException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmptyParameterException.class)
    public final ResponseEntity<SuccessResponse> onEmptyParameterException(final EmptyParameterException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity<SuccessResponse> error(final Exception exception, final HttpStatus httpStatus) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());

        GlobalSuccessResponse globalSuccessResponse = new GlobalSuccessResponse();
        return globalSuccessResponse.successResponseHandler(false, message, httpStatus);
    }

}
