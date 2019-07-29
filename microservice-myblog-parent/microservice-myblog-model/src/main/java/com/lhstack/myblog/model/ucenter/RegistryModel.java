package com.lhstack.myblog.model.ucenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "注册模型",description = "用户注册模型")
public class RegistryModel {
    @ApiModelProperty(value = "用户名",name = "username",example = "admin",required = true)
    private String username;
    @ApiModelProperty(value = "用户密码",name = "password",example = "admin",required = true)
    private String password;
}
