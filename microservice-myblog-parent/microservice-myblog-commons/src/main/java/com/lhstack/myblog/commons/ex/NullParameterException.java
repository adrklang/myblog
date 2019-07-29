package com.lhstack.myblog.commons.ex;

public class NullParameterException extends RuntimeException {

    public NullParameterException() {
    }

    public NullParameterException(String message) {
        super(message);
    }
}
