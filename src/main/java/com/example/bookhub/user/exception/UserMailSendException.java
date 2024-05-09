package com.example.bookhub.user.exception;

public class UserMailSendException extends RuntimeException {
    public UserMailSendException() {
    }

    public UserMailSendException (String message) {
        super(message);
    }

    public UserMailSendException (String message , Throwable cause) {
        super(message, cause);
    }
}
