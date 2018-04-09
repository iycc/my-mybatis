package com.iycc.mybatis.v2.config;

import java.io.IOException;

/**
 * Created by iycc on 2018/4/9.
 */
public class Configuration {
    private String scanPath;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    public Configuration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void build() throws IOException {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required .");
        }
        // TODO 扫描注解 加入 mapperRegistry
    }

    public static void main(String[] args) throws IOException {
        new Configuration().scanPath("com/iycc/mybatis/v2/config/mappers").build();
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
}
