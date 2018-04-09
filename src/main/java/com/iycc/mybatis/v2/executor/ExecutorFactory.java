package com.iycc.mybatis.v2.executor;

import com.iycc.mybatis.v2.config.Configuration;

/**
 * Created by iycc on 2018/4/9.
 */
public class ExecutorFactory {
    private static final String SIMPLE = "SIMPLE";
    private static final String CACHING = "CACHING";


    public static Executor DEFAULT(Configuration configuration) {
        return get(SIMPLE, configuration);
    }

    public static Executor get(String key, Configuration configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new SimpleExecutor(configuration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new CachingExecutor(new SimpleExecutor(configuration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE,CACHING
    }
}
