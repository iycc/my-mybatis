package com.iycc.mybatis.v1.client;

import com.iycc.mybatis.v1.core.Configuration;
import com.iycc.mybatis.v1.core.SimpleExecutor;
import com.iycc.mybatis.v1.core.SqlSession;

/**
 * Created by iycc on 2018/4/4.
 */
public class BootStrap {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(2);
        System.out.println(test);
    }
}
