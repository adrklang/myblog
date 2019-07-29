package com.lhstack.myblog.model.ucenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "blog_user_role")
@ApiModel(discriminator = "用户所属角色",value = "用户所属角色")
public class BlogRole implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名称",name = "roleName",example = "admin",readOnly = true)
    private String roleName;

    @Column(name = "role_meta_data")
    @ApiModelProperty(value = "角色元数据",name = "roleMetaData",example = "管理员",readOnly = true)
    private String roleMetaData;

}
