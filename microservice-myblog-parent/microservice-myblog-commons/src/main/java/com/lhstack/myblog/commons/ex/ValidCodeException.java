package com.lhstack.myblog.commons.ex;

import lombok.Data;

@Data
public class ValidCodeException extends RuntimeException {
    private Integer type;
    public ValidCodeException(Integer type) {
        this.type = type;
    }

    public ValidCodeException(Integer type,String message) {
        super(message);
        this.type = type;
    }
}
