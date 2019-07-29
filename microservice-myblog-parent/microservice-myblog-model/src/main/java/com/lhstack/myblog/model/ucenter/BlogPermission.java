package com.lhstack.myblog.model.ucenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blog_user_permission")
@Data
@Accessors(chain = true)
@ApiModel(value = "角色所属权限",description = "权限信息")
public class BlogPermission implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(name = "permission_name")
    @ApiModelProperty(value = "权限名称",name = "permissionName",example = "myblog_permission_add",readOnly = true)
    private String permissionName;

    @Column(name = "permission_meta_data")
    @ApiModelProperty(value = "权限元数据",name = "permissionMetaData",example = "添加权限",readOnly = true)
    private String permissionMetaData;

    @Column(name = "role_id")
    @ApiModelProperty(hidden = true)
    private Long roleId;
}
