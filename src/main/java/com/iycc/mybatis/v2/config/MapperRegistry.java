package com.iycc.mybatis.v2.config;

import com.iycc.mybatis.v1.client.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iycc on 2018/4/9.
 */
public class MapperRegistry {
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    public MapperRegistry() {
        methodSqlMapping.put("com.iycc.mybatis.v2.config.mappers.TestMapper.selectByPrimaryKey",
                new MapperData("select * from test where id = %d",Test.class));
    }

    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }

    public MapperData get(String nameSpace) {
        return methodSqlMapping.get(nameSpace);
    }
}
