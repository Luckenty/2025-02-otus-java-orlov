package ru.otus.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;

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
        private final HashSet<String> annotatedMethods;

        DemoInvocationHandler(Object myClass) {
            this.myClass = myClass;
            this.annotatedMethods = new HashSet<>();
            var methods = myClass.getClass().getDeclaredMethods();
            for (var method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    annotatedMethods.add(MethodUtils.prettyPrint(method));
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(MethodUtils.prettyPrint(method))) {
                logger.info("executed method: {}, param: {}", method.getName(), Arrays.toString(args));
                return method.invoke(myClass, args);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
