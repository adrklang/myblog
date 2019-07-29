package com.lhstack.myblog.utils;

import java.util.UUID;

public class EncryptUtils {
    public static String salt(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
