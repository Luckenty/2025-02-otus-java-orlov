package ru.otus.framework;

public class TestFramework {

    public static void run(Class<?> clazz) {
        try {
            var className = clazz.getName();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}