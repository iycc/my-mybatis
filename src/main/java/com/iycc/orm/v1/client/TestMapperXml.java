package com.iycc.orm.v1.client;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iycc on 2018/4/4.
 */
public class TestMapperXml {
    public static final String nameSpace = "com.iycc.orm.v1.client.TestMapper";

    public static final Map<String, String> methodSqlMapping = new HashMap<String, String>();

    static {
        methodSqlMapping.put("selectByPrimaryKey", "select * from test where id = %d");
    }
}
