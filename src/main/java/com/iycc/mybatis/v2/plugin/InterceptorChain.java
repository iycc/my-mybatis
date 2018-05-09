package com.iycc.mybatis.v2.plugin;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InterceptorChain {

    private final List<Interceptor> interceptors = new LinkedList<Interceptor>();

    /**
     * 每一个拦截器对目标类都进行一次代理
     * @param target
     * @return 层层代理后的对象
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

}
