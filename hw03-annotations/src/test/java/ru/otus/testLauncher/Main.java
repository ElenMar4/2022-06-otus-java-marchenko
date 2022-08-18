package ru.otus.testLauncher;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        new TestLauncher("ru.otus.tests.CalculatorTest" ).runTests();
    }
}
