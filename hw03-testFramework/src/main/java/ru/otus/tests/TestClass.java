package ru.otus.tests;

import ru.otus.framework.annotations.After;
import ru.otus.framework.annotations.Before;
import ru.otus.framework.annotations.Test;

public class TestClass {

    private final DataClass data = new DataClass();

    @Before
    public void beforeTest1() {
        data.setFirstVal(0);
    }

    @Before
    public void beforeTest2() {
        data.setSecondVal(0);
    }

    @Before
    public void beforeTest3() {
        data.setThirdVal(0);
    }

    @Test
    public void sumAllValuesTest() {
        // given
        data.setFirstVal(5);
        data.setSecondVal(10);
        data.setThirdVal(3);

        // when
        var expected = 18;
        var result = data.getFirstVal() + data.getSecondVal() + data.getThirdVal();

        // then
        var assertion = Integer.compare(expected, result);
        if (assertion != 0) throw new RuntimeException("Assertion failed!");
    }

    @Test
    public void divideFirstOnSecondTest() {
        // given
        data.setFirstVal(10);
        data.setSecondVal(5);

        // when
        var expected = 2;
        var result = data.getFirstVal() / data.getSecondVal();

        // then
        var assertion = Integer.compare(expected, result);
        if (assertion != 0) throw new RuntimeException("Assertion failed!");
    }

    @Test
    public void subTest() {
        // given
        data.setFirstVal(5);
        data.setSecondVal(10);

        // when
        var expected = 2;
        var result = data.getFirstVal() - data.getSecondVal();

        // then
        var assertion = Integer.compare(expected, result);
        if (assertion != 0) throw new RuntimeException("Assertion failed!");
    }

    @Test
    public void exceptionTest() {
        // given
        data.setFirstVal(12);
        data.setSecondVal(0);
        data.setThirdVal(11);

        // when
        var expected = 0;
        var result = data.getFirstVal() / data.getSecondVal();

        // then
        var assertion = Integer.compare(expected, result);
        if (assertion != 0) throw new RuntimeException("Assertion failed!");
    }


    @After
    public void afterTest1() {
        data.setFirstVal(null);
    }

    @After
    public void afterTest2() {
        data.setSecondVal(null);
    }

    @After
    public void afterTest3() {
        data.setThirdVal(null);
    }
}