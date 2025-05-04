package ru.otus.tests;

import ru.otus.framework.TestFramework;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        TestFramework.run(TestCases.class);
    }
}