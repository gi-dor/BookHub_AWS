package com.example.bookhub.admin.exception;

public class AlreadyAdminIdException extends RuntimeException{

    public AlreadyAdminIdException(String message){
        super(message);
    }

    public AlreadyAdminIdException(String message, Throwable cause){
        super(message, cause);
    }
}
