package com.goone.mangone.api.exception;

import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.utils.ManageMessageApplication;
import com.goone.mangone.api.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> getAllRequestFieldErrors(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        GenericResponse genericResponse = ValidationUtils.formatRequestFieldErrors(bindingResult);
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueFieldException.class)
    public ResponseEntity<GenericResponse> getUniqueFieldError(UniqueFieldException e) {
        GenericResponse genericResponse = ValidationUtils.formatFieldErrors(HttpStatus.BAD_REQUEST.value(), e.getField(), e.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistEntityException.class)
    public ResponseEntity<GenericResponse> getNotExistEntityError(NotExistEntityException e) {
        GenericResponse genericResponse = ValidationUtils.formatFieldErrors(HttpStatus.NOT_FOUND.value(), e.getField(), e.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveImageException.class)
    public ResponseEntity<GenericResponse> getNotExistEntityError(SaveImageException e) {
        GenericResponse genericResponse = ValidationUtils.formatFieldErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getField(), e.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericResponse> getGeneralException(AuthenticationException e) {
        GenericResponse genericResponse = new GenericResponse(StatusResponse.ERROR, manageMessageApplication.getMessage(ManageMessageApplication.ERROR_AUTHENTICATION_SIGNIN));
        return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> getGeneralException(Exception e) {
        GenericResponse genericResponse = new GenericResponse(StatusResponse.ERROR, e.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
