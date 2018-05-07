package com.goone.mangone.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotExistEntityException extends RuntimeException {
    private String field;
    private String message;
}
