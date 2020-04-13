package com.comarch.employee.exception;

public class RemunerationNotFoundException extends RuntimeException {

    public RemunerationNotFoundException() {
    }

    public RemunerationNotFoundException(String message) {
        super(message);
    }

    public RemunerationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemunerationNotFoundException(Throwable cause) {
        super(cause);
    }

    public RemunerationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
