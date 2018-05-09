package com.iycc.mybatis.v2.config;

import com.iycc.mybatis.v1.client.Test;
import com.iycc.mybatis.v2.annotation.Select;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iycc on 2018/4/9.
 */
public class MapperRegistry {
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    private String mapperPath;

    public MapperRegistry() {
        methodSqlMapping.put("com.iycc.mybatis.v2.config.mappers.TestMapper.selectByPrimaryKey",
                new MapperData("select * from test where id = %d",Test.class));
    }

    /**
     * 扫描mapper所在package，将sql和方法注册到Map中
     *
     * @param mapperPath
     */
    public void registry(String mapperPath) {
        this.mapperPath = mapperPath;
        initMethodSqlMap();
    }

    private void initMethodSqlMap() {
        try {
            List<Class> clzList = ClassPathHelper.getClasses(mapperPath);
            clzList.stream().filter(x -> x.isInterface()).forEach( c -> {
                Method[] methods = c.getDeclaredMethods();
                for(Method m : methods) {
                    if(m.isAnnotationPresent(Select.class)) {
                        String sql = m.getAnnotation(Select.class).value();
                        methodSqlMapping.put(
                                m.getDeclaringClass().getName() + "." + m.getName(),
                                new MapperData<>(sql, m.getReturnType()));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(methodSqlMapping);
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
