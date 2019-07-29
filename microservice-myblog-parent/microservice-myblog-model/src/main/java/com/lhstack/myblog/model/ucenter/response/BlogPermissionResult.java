package com.lhstack.myblog.model.ucenter.response;

import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.ResultCode;
import com.lhstack.myblog.model.ucenter.BlogPermission;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlogPermissionResult extends ResponseResult {
    private BlogPermission blogPermission;
    public BlogPermissionResult(ResultCode resultCode,BlogPermission blogPermission) {
        super(resultCode);
        this.blogPermission = blogPermission;
    }
}
