package com.lhstack.myblog.model.ucenter.request;

import com.lhstack.myblog.commons.model.request.IRequest;
import com.lhstack.myblog.model.ucenter.BlogRole;
import com.lhstack.myblog.model.ucenter.BlogUser;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryRoleRequest extends QueryRequest implements IRequest {
    private BlogRole blogRole;
    private BlogUser blogUser;
}
