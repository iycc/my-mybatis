package com.iycc.mybatis.v2.config.mappers;

import com.iycc.mybatis.v1.client.Test;
import com.iycc.mybatis.v2.annotation.Select;

/**
 * Created by iycc on 2018/4/9.
 */
public interface TestMapper {
    @Select("select * from test where id = %d")
    Test selectByPrimaryKey(Integer userId);
}
