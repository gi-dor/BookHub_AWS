package com.example.bookhub.user.exception;


public class UserJoinException extends RuntimeException {

    public UserJoinException() {
    }

    public UserJoinException(String message) {
        super(message);
    }

    public UserJoinException(String message , Throwable cause) {
        super(message, cause);
    }
}
