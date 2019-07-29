package com.lhstack.myblog.model.ucenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "blog_user")
@Data
@Accessors(chain = true)
@ApiModel(value = "Blog用户",description = "用户相关信息")
public class BlogUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "u_create_time")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @Column(name = "u_icon")
    @ApiModelProperty(value="用户头像",name="icon",example="data:image/jpeg;base64,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",required = false)
    private String icon;

    @Column(name = "u_salt")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    @Column(name = "u_ip")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ip;

    @Column(name = "u_last_login_time")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date lastLoginTime;

    @Column(name = "u_nick_name")
    @ApiModelProperty(value = "用户昵称",name = "nickName",example = "宝宝",required = false)
    private String nickName;

    @Column(name = "u_username")
    @ApiModelProperty(value = "用户名",name = "username",example = "admin",required = true)
    private String username;

    @Column(name = "u_password")
    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "u_email")
    @ApiModelProperty(value = "邮箱",name = "email",example = "lhstack@foxmail.com",required = false)
    private String email;

    @Column(name = "u_mobile")
    @ApiModelProperty(value = "手机号",name = "mobile",example = "13778569546",required = false)
    private String mobile;

    @Column(name = "u_like")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long like;

    @Column(name = "u_look")
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long look;

    @ApiModelProperty(hidden = true)
    @Column(name = "u_role_id")
    @JsonIgnore
    private Long roleId;

}
