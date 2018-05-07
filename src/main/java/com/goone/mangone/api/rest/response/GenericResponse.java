package com.goone.mangone.api.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(using = ResponseSerializer.class)
public class GenericResponse {
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String MESSAGE = "message";
    private StatusResponse status;
    private String message;
    private Object data;

    public GenericResponse(){
    }

    public GenericResponse(StatusResponse status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(StatusResponse status, Object data){
        this.status = status;
        this.data = data;
    }

    public GenericResponse(StatusResponse status, String message){
        this.status = status;
        this.message = message;
    }
}
