package com.lhstack.myblog.registry.controller;

import com.lhstack.myblog.api.ucenter.UserRegistryControllerApi;
import com.lhstack.myblog.commons.ex.ValidCodeException;
import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.RegistryModel;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;
import com.lhstack.myblog.registry.fallback.FollbackFactory;
import com.lhstack.myblog.registry.service.IUserService;
import com.lhstack.myblog.validcode.service.ValidCodeService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("registry")
@AllArgsConstructor
public class UserRegistryController implements UserRegistryControllerApi {

    private final IUserService userService;
    private final ValidCodeService validCodeService;
    @Override
    @PostMapping("registry")
    public BlogUserResult registry(@RequestBody RegistryModel registryModel, @RequestParam(required = false,value = "validCode",defaultValue = "") String validCode, HttpSession session) {
        if(StringUtils.isEmpty(validCode))
            throw new ValidCodeException(1,"验证码为空");
        Object code = session.getAttribute("code");
        if(code == null)
            throw new ValidCodeException(3,"验证码不存在，请先获取验证码");
        if(!code.toString().equalsIgnoreCase(validCode))
            throw new ValidCodeException(2,"验证码不正确，请重新输入");
        BlogUser blogUser = new BlogUser();
        blogUser.setUsername(registryModel.getUsername()).setPassword(registryModel.getPassword());
        BlogUserResult registry = userService.registry(blogUser);
        return registry;
    }

    @Override
    @GetMapping("validcode")
    @ResourceLimit(key = "validCode",fallbackFactory = FollbackFactory.class,method = "fallback")
    public void validCode(HttpServletResponse response, HttpSession httpSession) throws IOException {
        response.setHeader("Pragma", "no-cache");    //HTTP   1.0
        response.setHeader("Cache-Control", "no-cache");//HTTP   1.1
        response.setDateHeader("Expires", 0);      //在代理服务器端防止缓冲
        response.setContentType("image/gif");
        String validCode = validCodeService.createValidCode(response.getOutputStream());
        httpSession.setMaxInactiveInterval(300);
        httpSession.setAttribute("code",validCode);
    }
}
