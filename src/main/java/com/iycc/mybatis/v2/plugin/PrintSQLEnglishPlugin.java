package com.iycc.mybatis.v2.plugin;

import com.iycc.mybatis.v2.annotation.PluginPoint;
import com.iycc.mybatis.v2.config.MapperRegistry;
import com.iycc.mybatis.v2.statement.DefaultStatementHandler;
import com.iycc.mybatis.v2.statement.StatementHandler;

@PluginPoint(DefaultStatementHandler.class)
public class PrintSQLEnglishPlugin implements Interceptor  {
    private boolean printSQL = true;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MapperRegistry.MapperData mapperData = (MapperRegistry.MapperData) invocation.getArgs()[0];
        String sql = mapperData.getSql();
        String params = (String) invocation.getArgs()[1];
        System.out.println("\n=====================PrintSQLEnglishPlugin====================");
        if(printSQL) {
            System.out.printf("intercept sql = %s, params = %s \n",sql,  params);
        }
        // 执行target被代理对象的方法
        return invocation.proceed();
    }

    /**
     * target: Executor or ParameterHandler or ResultSetHandler or StatementHandler
     */
    @Override
    public Object plugin(Object target) {
        return PluginProxy.proxy(target, this);
    }
}
