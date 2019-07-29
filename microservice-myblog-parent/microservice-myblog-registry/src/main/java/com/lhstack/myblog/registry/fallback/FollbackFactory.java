package com.lhstack.myblog.registry.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhstack.myblog.commons.model.response.LimitCode;
import com.lhstack.myblog.commons.model.response.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FollbackFactory {
    public static void fallback(HttpServletResponse response, HttpSession httpSession) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseResult responseResult = new ResponseResult(LimitCode.LIMIT_SUCCESS);
        objectMapper.writeValue(response.getOutputStream(),responseResult);
    }
}
