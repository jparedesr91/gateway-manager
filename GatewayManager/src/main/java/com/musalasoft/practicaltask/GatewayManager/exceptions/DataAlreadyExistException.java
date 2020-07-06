package com.musalasoft.practicaltask.GatewayManager.exceptions;

public class DataAlreadyExistException extends RuntimeException {
    public DataAlreadyExistException() {
    }

    public DataAlreadyExistException(String s) {
        super(s);
    }

    public DataAlreadyExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DataAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public DataAlreadyExistException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
