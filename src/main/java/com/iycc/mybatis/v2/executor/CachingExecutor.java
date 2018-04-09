package com.iycc.mybatis.v2.executor;

import com.iycc.mybatis.v2.config.Configuration;
import com.iycc.mybatis.v2.config.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iycc on 2018/4/9.
 */
public class CachingExecutor implements Executor {
    private Configuration configuration;

    private SimpleExecutor delegate;

    private Map<String,Object> localCache = new HashMap();

    public CachingExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExecutor(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        Object result = localCache.get(mapperData.getSql());
        if( null != result){
            System.out.println("缓存命中");
            return (E)result;
        }
        result =  (E) delegate.query(mapperData,parameter);
        localCache.put(mapperData.getSql(),result);
        return (E)result;
    }
}
