package ru.otus;

import ru.otus.AOP.Ioc;
import ru.otus.TestCalculation.TestLogging;
import ru.otus.TestCalculation.TestLoggingInterface;

public class Main {

    public static void main(String[] args) {

        final Class<TestLoggingInterface> loggingInterface = TestLoggingInterface.class;

        TestLoggingInterface myTestLogging = Ioc.createMyLogging(loggingInterface, new TestLogging());
        myTestLogging.calculation(6);
        myTestLogging.calculation(6, 1);
        myTestLogging.calculation(6, 1, "Hello!");
        myTestLogging.calculation();
    }
}
