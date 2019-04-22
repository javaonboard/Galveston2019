package com.galveston.error;

import lombok.Getter;

@Getter
public class GenericException extends Exception {
    private String message;
    public GenericException(String message){
        super(message);
        this.message = message;
    }

}
