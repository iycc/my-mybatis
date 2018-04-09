package com.iycc.mybatis.v1.core;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iycc on 2018/4/4.
 */
public class Configuration {
    public <T> T getMapper(SqlSession sqlSession, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz}, new MapperProxy(sqlSession, clazz));
    }

    static class TestMapperXml{
        public static final String nameSpace = "com.iycc.mybatis.v1.client.TestMapper";

        public static final Map<String, String> methodSqlMapping = new HashMap<>();

        static {
            methodSqlMapping.put("selectByPrimaryKey", "select * from test where id = %d");
        }
    }
}
