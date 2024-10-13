package org.leetcode.learn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LearnProxy {
    public static void main(String[] args) {
        Hello realHello = new HelloImpl();

        Hello proxyHello = (Hello) Proxy.newProxyInstance(
                realHello.getClass().getClassLoader(),
                realHello.getClass().getInterfaces(),
                new HelloInvocationHandler(realHello)
        );
        proxyHello.sayHello();
    }
}

interface Hello {
    void sayHello();
}

// 实现 InvocationHandler，用于处理方法调用
class HelloInvocationHandler implements InvocationHandler {
    private final Object target;

    public HelloInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method call");
        Object result = method.invoke(target, args);
        System.out.println("After method call");
        return result;
    }
}

// 实现真实的接口
class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

