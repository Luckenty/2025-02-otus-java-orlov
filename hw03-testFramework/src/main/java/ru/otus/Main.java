package ru.otus;

import ru.otus.framework.TestFramework;
import ru.otus.tests.TestClass;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running test cases...");
        TestFramework.run(TestClass.class);
    }
}