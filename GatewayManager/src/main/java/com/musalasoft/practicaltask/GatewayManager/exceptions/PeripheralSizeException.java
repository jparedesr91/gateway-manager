package com.musalasoft.practicaltask.GatewayManager.exceptions;

public class PeripheralSizeException extends RuntimeException {
    public PeripheralSizeException() {
    }

    public PeripheralSizeException(String s) {
        super(s);
    }

    public PeripheralSizeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PeripheralSizeException(Throwable throwable) {
        super(throwable);
    }

    public PeripheralSizeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
