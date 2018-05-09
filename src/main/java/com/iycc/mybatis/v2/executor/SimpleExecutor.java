package com.iycc.mybatis.v2.executor;

import com.iycc.mybatis.v2.config.Configuration;
import com.iycc.mybatis.v2.config.MapperRegistry;
import com.iycc.mybatis.v2.statement.DefaultStatementHandler;
import com.iycc.mybatis.v2.statement.StatementHandler;

/**
 * Created by iycc on 2018/4/9.
 */
public class SimpleExecutor implements Executor {
    public static final int INT = 1;
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
        StatementHandler statementHandler = new DefaultStatementHandler(configuration);
        // plugin step4：在可埋点的类中，使用代理来进行实际的业务处理
        StatementHandler statementHandlerProxy =
                (StatementHandler) configuration.getInterceptorChain().pluginAll(statementHandler);
        return (E) statementHandlerProxy.query(mapperData, parameter);
    }
}
