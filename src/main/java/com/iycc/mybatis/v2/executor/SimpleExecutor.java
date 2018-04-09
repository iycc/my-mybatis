package com.iycc.mybatis.v2.executor;

import com.iycc.mybatis.v2.config.Configuration;
import com.iycc.mybatis.v2.config.MapperRegistry;
import com.iycc.mybatis.v2.statement.StatementHandler;

/**
 * Created by iycc on 2018/4/9.
 */
public class SimpleExecutor implements Executor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler handler = new StatementHandler(configuration);
        return (E) handler.query(mapperData, parameter);
    }
}
