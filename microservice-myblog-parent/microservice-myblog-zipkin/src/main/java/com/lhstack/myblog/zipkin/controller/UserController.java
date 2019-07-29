package com.lhstack.myblog.zipkin.controller;

import com.lhstack.myblog.api.ucenter.UserControllerApi;
import com.lhstack.myblog.model.ucenter.BlogPermission;
import com.lhstack.myblog.model.ucenter.BlogRole;
import com.lhstack.myblog.model.ucenter.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("user")
@RefreshScope
public class UserController implements UserControllerApi {

    @Value("${spring.zipkin.base-url:空}")
    private String zipServer;

    @Autowired(required = false)
    private LoadBalancerClient loadBalancerClient;

    @Autowired(required = false)
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;

    @Autowired(required = false)
    @Qualifier("loadBalance")
    private RestTemplate restTemplate1;

    @Value("${message:空}")
    public String message;

    @GetMapping("loadBalance/info")
    public BlogUser loadBalanceRestTemplate(){
        BlogUser resultBlogUser = restTemplate1.getForObject("http://microservice-myblog-zipkin/user/info", BlogUser.class);
        return resultBlogUser;
    }

    @GetMapping("getRestTemplate/info")
    public BlogUser getRestTemplate(){
        ServiceInstance choose = loadBalancerClient.choose("microservice-myblog-zipkin");
        URI uri = choose.getUri();
        BlogUser resultBlogUser = restTemplate.getForObject(uri + "/user/info", BlogUser.class);
        return resultBlogUser;
    }

    @GetMapping("global/info")
    public String globalInfo(){
        return message;
    }
    @GetMapping("info")
    public BlogUser getUser(){
        BlogUser blogUser = new BlogUser();
        blogUser.setNickName("lhstack")
                .setUsername("admin")
                .setPassword("123456");
        System.out.println("服务3");
        return blogUser;
    }

    @Override
    @PostMapping("save")
    public BlogUser save(@RequestBody BlogUser blogUser) {
        System.out.println(blogUser);
        blogUser.setCreateTime(new Date())
                .setLastLoginTime(new Date())
                .setIp("10.10.10.10")
                .setLike(10l)
                .setLook(10l)
                .setRoleId(10l);
        return blogUser;
    }

    @Override
    @PostMapping("saverole")
    public BlogRole save(@RequestBody BlogRole blogRole) {
        return blogRole;
    }

    @GetMapping("config")
    public String config(){
        return zipServer;
    }

    @GetMapping("message")
    public String message(){
        return message;
    }

    @Override
    @PostMapping("savepermission")
    public BlogPermission save(@RequestBody BlogPermission blogPermission) {
        return blogPermission;
    }
}
