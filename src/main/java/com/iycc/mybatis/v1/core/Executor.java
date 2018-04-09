package com.iycc.mybatis.v1.core;

/**
 * Created by iycc on 2018/4/4.
 */
public interface Executor {
    <T> T query(String sql, Object parameter);
}
