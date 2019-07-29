package com.lhstack.myblog.commons.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;

    private QueryResult(List<T> list,Long total){
        this.list = list;
        this.total = total;
    }

    public static <T> QueryResult<T> build(List<T> list, Long total){
        return new QueryResult<T>(list,total);
    }
}