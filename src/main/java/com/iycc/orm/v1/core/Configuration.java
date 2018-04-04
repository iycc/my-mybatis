package com.iycc.orm.v1.core;

import java.lang.reflect.Proxy;

/**
 * Created by iycc on 2018/4/4.
 */
public class Configuration {
    public <T> T getMapper(SqlSession sqlSession, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz}, new MapperProxy(sqlSession, clazz));
    }
}
