package com.iycc.mybatis.v2.config;

import com.iycc.mybatis.v2.plugin.Interceptor;
import com.iycc.mybatis.v2.plugin.InterceptorChain;

import java.io.IOException;

/**
 * Created by iycc on 2018/4/9.
 */
public class Configuration {
    private String scanPath;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    private InterceptorChain interceptorChain = new InterceptorChain();

    public Configuration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void build() throws IOException {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required .");
        }
        mapperRegistry.registry(scanPath);
        addPlugins();

    }

    /**
     * plugin step3：注册所有的plugin
     */
    private void addPlugins() {
        try {
            ClassPathHelper.getClasses("com.iycc.mybatis.v2.plugin").stream().filter( cls -> {
                return !cls.isInterface() && Interceptor.class.isAssignableFrom(cls);
            }).forEach(cls -> {
                try {
                    interceptorChain.addInterceptor((Interceptor)cls.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    public InterceptorChain getInterceptorChain() {
        return interceptorChain;
    }
}
