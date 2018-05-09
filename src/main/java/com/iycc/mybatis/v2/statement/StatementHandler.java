package com.iycc.mybatis.v2.statement;

import com.iycc.mybatis.v2.config.MapperRegistry;

public interface StatementHandler {
    <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception;
}
