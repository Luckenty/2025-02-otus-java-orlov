package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Object myClass;
        private final Set<Method> annotatedMethods;

        DemoInvocationHandler(Object myClass) {
            this.myClass = myClass;
            this.annotatedMethods = new HashSet<>();
            var methods = myClass.getClass().getDeclaredMethods();
            for (var method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    annotatedMethods.add(method);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (var am : annotatedMethods) {
                if (am.toString().equals(method.toString())) {
                    logger.info("executed method: {}, param: {}", method.getName(), Arrays.stream(method.getParameters()).toList());
                    return am.invoke(myClass, args);
                }
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
