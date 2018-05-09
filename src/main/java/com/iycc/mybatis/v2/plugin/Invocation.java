package com.iycc.mybatis.v2.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Invocation 这个类，其实就是封装了被代理对象执行调用时所需的3个要素：被代理的对象、需要执行的方法、方法的参数
 *
 */

public class Invocation {

    private Object target;

    private Method method;

    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

}
