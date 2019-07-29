package com.lhstack.myblog.model.ucenter.response;

import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.ResultCode;
import com.lhstack.myblog.model.ucenter.BlogRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlogRoleResult extends ResponseResult {
    private BlogRole blogRole;

     public BlogRoleResult(ResultCode resultCode, BlogRole blogRole){
         super(resultCode);
         this.blogRole = blogRole;
     }
}
