package com.lhstack.myblog.fallback;

import com.lhstack.myblog.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;

public class FallbackFactory {
    public static String message(String message, HttpServletRequest request){
        String ip = IpUtils.ip(request);
        System.out.println("ip:" + ip);
        return message + "/resourceLimit" + "ip:" + ip;
    }
}
