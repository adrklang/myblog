package com.lhstack.myblog.limit.service;

import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.limit.model.LimitType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class TestService {
    @ResourceLimit(key = "helloMap",count = 20,seconds = 5,fallbackFactory = FallBackFactory.class,method = "helloMap")
    public Map<String,String> helloMap(){
        Map<String,String> map = new HashMap<>();
        map.put("author","LH");
        map.put("age","21");
        return map;
    }
    @ResourceLimit(key = "helloList",count = 10,seconds = 2,type = LimitType.IP,fallbackFactory = FallBackFactory.class,method = "helloList")
    public List<String> helloList(){
        List<String> list = new LinkedList<>();
        list.add("LH");
        list.add("21");
        return list;
    }

    public static class FallBackFactory{
        public static Map<String,String> helloMap(){
            Map<String,String> map = new HashMap<>();
            map.put("author","LH");
            map.put("age","21");
            map.put("limit","true");
            return map;
        }
        public static List<String> helloList(){
            List<String> list = new LinkedList<>();
            list.add("LH");
            list.add("21");
            list.add("limit:true");
            return list;
        }
    }
}