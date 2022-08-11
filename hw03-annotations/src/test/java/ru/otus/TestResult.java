package ru.otus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestResult implements UnitTestResult{
    private int successfulTest;
    private int failedTest;
    private int allRunTest;

    private final Map<Method, Boolean> resultRunMethods = new HashMap<>();

    public Map<Method, Boolean> getResultRunMethods() {
        return resultRunMethods;
    }

    public void addResultRunMethods(Method method, Boolean flag) {
        resultRunMethods.put(method, flag);
    }

    public int getSuccessfulTest() {
        return successfulTest;
    }

    public void setSuccessfulTest(int successfulTest) {
        this.successfulTest = successfulTest;
    }

    public int getFailedTest() {
        return failedTest;
    }

    public void setFailedTest(int failedTest) {
        this.failedTest = failedTest;
    }

    public int getAllRunTest() {
        return allRunTest;
    }

    public void setAllRunTest(int allRunTest) {
        this.allRunTest = allRunTest;
    }

    public StringBuilder printResultRunTest (){
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Method, Boolean> pair : getResultRunMethods().entrySet()){
            str.append("Метод: ").append(pair.getKey()).append(" - Результат выполнения: ").append(pair.getValue()).append("\n");
        }
        str.append("TestResult{" + "\ntest all run = ").append(allRunTest)
                .append(", \ntest failed = ").append(failedTest)
                .append(", \ntest successful = ").append(successfulTest)
                .append('}');
        return str;
    }
}
