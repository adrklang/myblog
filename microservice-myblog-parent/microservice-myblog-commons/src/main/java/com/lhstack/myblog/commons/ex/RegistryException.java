package com.lhstack.myblog.commons.ex;

import lombok.Data;

@Data
public class RegistryException extends RuntimeException {
    private Integer type;
    public RegistryException(Integer type) {
        this.type = type;
    }

    public RegistryException(String message,Integer type) {
        super(message);
        this.type = type;
    }
}
