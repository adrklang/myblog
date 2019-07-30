package com.lhstack.myblog.fallback;

import com.lhstack.myblog.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;

public class FallbackFactory {
    public static String message(String message){
        System.out.println("ip:");
        return message + "/resourceLimit" + "ip:";
    }
}
