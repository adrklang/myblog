package com.lhstack.myblog.validcode.service;

import com.lhstack.myblog.validcode.model.ValidCode;

import java.io.IOException;
import java.io.OutputStream;

public interface ValidCodeService {
    ValidCode resultValidCode() throws IOException;
    public String createValidCode(OutputStream outputStream, String imageType) throws IOException;
    public String createValidCode(OutputStream outputStream) throws IOException ;
}
