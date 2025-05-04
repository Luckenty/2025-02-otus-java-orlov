package ru.otus.framework;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;
import ru.otus.tests.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFramework {


    public static void run(Class<TestClass> clazz) {
        try {
            var beforeMethods = Arrays.stream(clazz.getDeclaredMethods()).filter(it -> it.isAnnotationPresent(Before.class)).toList();
            var testMethods = Arrays.stream(clazz.getDeclaredMethods()).filter(it -> it.isAnnotationPresent(Test.class)).toList();
            var afterMethods = Arrays.stream(clazz.getDeclaredMethods()).filter(it -> it.isAnnotationPresent(After.class)).toList();

            int testQuantity = 0;
            int passed = 0;
            int failed = 0;
            List<String> passedMethodNames = new ArrayList<>(testMethods.size());
            List<String> failedMethodNames = new ArrayList<>(testMethods.size());

            for (Method testMethod : testMethods) {
                testQuantity++;
                var instance = clazz.getDeclaredConstructor().newInstance();

                execAllBeforeMethods(beforeMethods, instance);

                try {
                    testMethod.setAccessible(true);
                    testMethod.invoke(instance);
                    passedMethodNames.add(testMethod.getName());
                    passed++;
                } catch (Exception e) {
                    failedMethodNames.add(testMethod.getName());
                    failed++;
                }

                execAllAfterMethods(afterMethods, instance);

            }

            printTestRunResult(new Report(testQuantity, passed, failed, passedMethodNames, failedMethodNames));

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execAllBeforeMethods(List<Method> beforeMethods, TestClass instance) {
        try {
            for (Method method : beforeMethods) {
                method.setAccessible(true);
                method.invoke(instance);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execAllAfterMethods(List<Method> beforeMethods, TestClass instance) {
        try {
            for (Method method : beforeMethods) {
                method.setAccessible(true);
                method.invoke(instance);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTestRunResult(Report report) {
        System.out.println("Test run result:\n"
                + "Tests executed: " + report.testsQuantity() + "\n"
                + "Tests Passed: " + report.passed() + "\n"
                + report.passedMethods().toString() + "\n"
                + "Tests Failed: " + report.failed() + "\n"
                + report.failedMethods().toString() + "\n");
    }
}