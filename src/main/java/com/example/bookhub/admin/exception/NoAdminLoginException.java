package com.example.bookhub.admin.exception;

public class NoAdminLoginException extends RuntimeException{

    public NoAdminLoginException(String message){
        super(message);
    }

    public NoAdminLoginException(String message, Throwable cause){
        super(message, cause);
    }
}
