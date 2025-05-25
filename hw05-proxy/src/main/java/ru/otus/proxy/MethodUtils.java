package ru.otus.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MethodUtils {
    public static String prettyPrint(Method method) {
        return String.format("%s(%s)",
                method.getName(),
                Arrays.stream(method.getParameterTypes())
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", ")));
    }
}