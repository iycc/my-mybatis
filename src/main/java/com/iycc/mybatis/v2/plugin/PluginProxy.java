package com.iycc.mybatis.v2.plugin;

import com.iycc.mybatis.v2.annotation.PluginPoint;
import com.iycc.mybatis.v2.config.ClassPathHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class PluginProxy implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    private PluginProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    /**
     * 代理对象被执行时，调用拦截器链中的拦截器的intercept() --- 插件被执行
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理对象调用方法执行时，invoke被调用，在invoke中执行拦截器，在拦截器中执行plugin的逻辑，然后调用target
        // target可能是interceptorChain中的其它拦截器，也可能是真正的target（拦截器都执行完了，就是执行真正target的方法了）
        return interceptor.intercept(new Invocation(target, method, args));
    }

    public static Object proxy(Object target, Interceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        if (interfaces.length > 0) {
            Object proxy = Proxy.newProxyInstance(
                    type.getClassLoader(),
                    interfaces,
                    new PluginProxy(target, interceptor));
            return proxy;
        }
        return target;
    }

    /**
     *
     * @param interceptor
     * @return
     */
    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = new HashMap<Class<?>, Set<Method>>();

        try {
            ClassPathHelper.getClasses("com.iycc.mybatis.v2.plugin").stream().filter(cls -> {
                return !cls.isInterface() && Interceptor.class.isAssignableFrom(cls);
            }).forEach(cls -> {
                try {
                    PluginPoint pluginPoint = (PluginPoint)cls.getDeclaredAnnotation(PluginPoint.class);
                    if(pluginPoint != null) {
                        signatureMap.put(pluginPoint.value(), Collections.EMPTY_SET);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        return signatureMap;
    }

    /**
     * getAllInterfaces 找到所有“合法”的接口，比如需要排除interface java.io.Serializable---代理类实现的接口
     *
     */
    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        Set<Class<?>> interfaces = new HashSet<Class<?>>();
        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                } else {
                    // interface java.io.Serializable
                    System.out.println("过滤额外生成的interface：" + c.getName());
                }
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }

}
