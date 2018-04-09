package com.iycc.mybatis.v2.session;

import com.iycc.mybatis.v2.config.Configuration;
import com.iycc.mybatis.v2.config.MapperRegistry;
import com.iycc.mybatis.v2.executor.Executor;
import com.iycc.mybatis.v2.mapper.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * Created by iycc on 2018/4/9.
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public Configuration getConfiguration() {
        return configuration;
    }

    //关联起来
    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},new MapperProxy(this,clazz));
    }

    public <T> T selectOne(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }
}
