package com.lhstack.myblog.model.ucenter.response;

import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.ResultCode;
import com.lhstack.myblog.model.ucenter.BlogUserService;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlogUserServiceResult extends ResponseResult {
    private BlogUserService blogUserService;

    public BlogUserServiceResult(ResultCode resultCode,BlogUserService blogUserService) {
        super(resultCode);
        this.blogUserService = blogUserService;
    }
}
