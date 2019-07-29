package com.lhstack.myblog.model.ucenter.response;

import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.ResultCode;
import com.lhstack.myblog.model.ucenter.BlogUser;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlogUserResult extends ResponseResult {
    private BlogUser blogUser;
    public BlogUserResult(ResultCode resultCode,BlogUser blogUser) {
        super(resultCode);
        this.blogUser = blogUser;
    }
}
