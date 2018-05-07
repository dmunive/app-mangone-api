package com.goone.mangone.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SaveImageException extends RuntimeException {
    private String field;
    private String message;
}
