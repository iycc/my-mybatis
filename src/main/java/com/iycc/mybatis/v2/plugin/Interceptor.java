package com.iycc.mybatis.v2.plugin;

/**
 * plugin是基于拦截器做的，所有的plugin，必须实现此接口
 *
 * 实现了该接口的类 都要被执行，在哪执行呢？需要提前埋点
 * 按官方文档，拦截器可以拦截的点有：Executor，ParameterHandler，ResultSetHandler，StatementHandler
 * plugin step1：核心类 Interceptor InterceptorChain Invocation PluginProxy
 */
public interface Interceptor {

    /**
     * 具体拦截逻辑
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 对 target 进行代理
     * @param target
     * @return
     */
    Object plugin(Object target);

}

