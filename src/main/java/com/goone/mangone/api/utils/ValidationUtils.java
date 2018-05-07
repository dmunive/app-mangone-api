package com.goone.mangone.api.utils;

import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidationUtils {

    public static GenericResponse formatRequestFieldErrors(BindingResult bindingResult) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setStatus(StatusResponse.FAIL);
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        genericResponse.setData(errors);
        return genericResponse;
    }

    public static GenericResponse formatFieldErrors(Integer code, String field, String message) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setStatus(StatusResponse.FAIL);
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put(field, message);
        genericResponse.setData(errors);
        return genericResponse;
    }

}
