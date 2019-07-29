package com.lhstack.myblog.validcode.controller;

import com.lhstack.myblog.api.valid.ValidCodeControllerApi;
import com.lhstack.myblog.commons.model.response.CommonCode;
import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.validcode.service.ValidCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("valid")
public class ValidCodeController implements ValidCodeControllerApi {
    @Autowired
    private ValidCodeService codeService;

    @Override
    @GetMapping("code")
    public void createValid(HttpSession session, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");    //HTTP   1.0
        response.setHeader("Cache-Control", "no-cache");//HTTP   1.1
        response.setDateHeader("Expires", 0);      //在代理服务器端防止缓冲
        response.setContentType("image/gif");
        String validCode = codeService.createValidCode(response.getOutputStream());
        session.setAttribute("code",validCode);
    }

    @Override
    @GetMapping("code/valid")
    public ResponseResult validCode(HttpSession session,@RequestParam(required = true,name = "codeStr") String codeStr) {
        Object code = session.getAttribute("code");
        if(code.toString().equalsIgnoreCase(codeStr)){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
