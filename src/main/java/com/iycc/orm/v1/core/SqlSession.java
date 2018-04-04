package com.iycc.orm.v1.core;

/**
 * Created by iycc on 2018/4/4.
 */
public class SqlSession {
    Configuration configuration;
    Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T selectOne(String sql, Object parameter) {
        return executor.query(sql, parameter);
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(this, clazz);
    }
}
