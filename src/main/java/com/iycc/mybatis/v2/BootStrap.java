package com.iycc.mybatis.v2;

import com.iycc.mybatis.v1.client.Test;
import com.iycc.mybatis.v2.config.Configuration;
import com.iycc.mybatis.v2.config.mappers.TestMapper;
import com.iycc.mybatis.v2.executor.ExecutorFactory;
import com.iycc.mybatis.v2.session.SqlSession;

import java.io.IOException;

/**
 * Created by iycc on 2018/4/9.
 */
public class BootStrap {
    public static void main(String[] args) throws IOException {
        start();
    }
    private static void start() throws IOException {
        Configuration configuration = new Configuration();
        configuration.scanPath("com.iycc.mybatis.v2.config.mappers").build();
        SqlSession sqlSession = new SqlSession(configuration,
                ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHING.name(),configuration));
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
        System.out.println("cost:"+ (System.currentTimeMillis() -start));
    }
}
