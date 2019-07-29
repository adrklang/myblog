package com.lhstack.myblog.model.ucenter.request;

import com.lhstack.myblog.commons.model.request.IRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryRequest implements IRequest {
    private Integer page;
    private Integer size;
    private String sortord;
}
