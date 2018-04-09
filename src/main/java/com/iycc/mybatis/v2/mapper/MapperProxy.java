package com.iycc.mybatis.v2.mapper;

import com.iycc.mybatis.v2.config.MapperRegistry;
import com.iycc.mybatis.v2.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by iycc on 2018/4/9.
 */
public class MapperProxy<T> implements InvocationHandler {
    private final SqlSession sqlSession;
    private final Class<T> mappperInterface;

    public MapperProxy(SqlSession gpSqlSession, Class<T> clazz) {
        this.sqlSession = gpSqlSession;
        this.mappperInterface = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistry.MapperData mapperData =
                sqlSession.getConfiguration()
                        .getMapperRegistry().methodSqlMapping
                        .get(method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperData) {
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }
        return null;
    }
}
