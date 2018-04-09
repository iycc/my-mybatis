package com.iycc.mybatis.v2.executor;

import com.iycc.mybatis.v2.config.MapperRegistry;

/**
 * Created by iycc on 2018/4/9.
 */
public interface Executor {
    <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception;
}
