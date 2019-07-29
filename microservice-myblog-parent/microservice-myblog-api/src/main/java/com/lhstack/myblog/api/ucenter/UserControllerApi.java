package com.lhstack.myblog.api.ucenter;

import com.lhstack.myblog.model.ucenter.BlogPermission;
import com.lhstack.myblog.model.ucenter.BlogRole;
import com.lhstack.myblog.model.ucenter.BlogUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户操作接口",description = "用户的增删改查")
public interface UserControllerApi {

    @ApiOperation("保存用户")
    BlogUser save(BlogUser blogUser);

    @ApiOperation("保存角色")
    BlogRole save(BlogRole blogRole);


    @ApiOperation("保存权限")
    BlogPermission save(BlogPermission blogPermission);
}
