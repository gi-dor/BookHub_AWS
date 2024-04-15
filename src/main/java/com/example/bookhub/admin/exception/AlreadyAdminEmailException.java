package com.example.bookhub.admin.exception;

public class AlreadyAdminEmailException extends RuntimeException{

    public AlreadyAdminEmailException(String message){
        super(message);
    }

    public AlreadyAdminEmailException(String message, Throwable cause){
        super(message, cause);
    }
}
