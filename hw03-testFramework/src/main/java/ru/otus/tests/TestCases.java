package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestCases {

    private Integer a = null;
    private Integer b = null;
    private Integer c = null;

    @Before
    public void beforeTest1() {
        a = 0;
    }

    @Before
    public void beforeTest2() {
        b = 0;
    }

    @Before
    public void beforeTest3() {
        c = 0;
    }

    @Test
    public void testSum() {
        a = 5;
        b = 10;
        c = 3;

        var result = a + b + c;
    }

    @Test
    public void testDivide() {
        a = 10;
        b = 2;

        var result = a / b;
    }

    @Test
    public void testSub() {
        a = 100;
        b = 70;
        c = 20;

        var result = a - b - c;
    }


    @After
    public void afterTest1() {
        a = null;
    }

    @After
    public void afterTest2() {
        b = null;
    }

    @After
    public void afterTest3() {
        c = null;
    }
}