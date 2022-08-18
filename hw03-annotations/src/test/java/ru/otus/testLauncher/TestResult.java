package ru.otus.testLauncher;

import java.util.List;

public class TestResult implements UnitTestResult {

    private final List<ResultRunTest> results;

    public TestResult (Class<?> testClass, List<ResultRunTest> results){
        this.results = results;
    }

    public int countAllRunTest() {
        return results.size();
    }

    public int countSuccessfulTest() {
        int count = 0;
        for (ResultRunTest result : results) {
            if (result.getFlag()) {
                count++;
            }
        }
       return count;
    }

    public int countFailedTest() {
            int count = 0;
            for (ResultRunTest result : results){
                if (!result.getFlag()){
                    count++;
                }
        }
        return count;
    }

    public void printResultRunTest (){
        StringBuilder str = new StringBuilder();
        for (ResultRunTest result : results){
            str.append("Method: ").append(result.getMethodName()).append(" - Result: ").append(result.getFlag()).append("\n");
        }
        str.append("TestResult{" + "\ntest all run = ").append(countAllRunTest())
                .append(", \ntest failed = ").append(countFailedTest())
                .append(", \ntest successful = ").append(countSuccessfulTest())
                .append('}');
        System.out.println(str);
    }
}
