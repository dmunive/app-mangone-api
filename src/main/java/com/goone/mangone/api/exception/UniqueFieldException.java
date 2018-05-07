package com.goone.mangone.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UniqueFieldException extends RuntimeException {
    private String field;
    private String message;
}
