package com.lhstack.myblog.fallback;

public class FallbackFactory {
    public static String message(String message){
        System.out.println("这是限流回调");
        return message + "/resourceLimit";
    }
}
