package ru.otus.TestCalculation;

import ru.otus.AOP.Log;

public class TestLogging implements TestLoggingInterface {


    @Override
    @Log
    public void calculation(int param) {
        System.out.println("метод calculation с одним параметром\n");
    }

    //    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println("метод calculation с двумя параметрами\n");
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        System.out.println("метод calculation с тремя параметрами\n");
    }

    @Log
    @Override
    public void calculation() {
        System.out.println("метод calculation без параметров\n");
    }
}
