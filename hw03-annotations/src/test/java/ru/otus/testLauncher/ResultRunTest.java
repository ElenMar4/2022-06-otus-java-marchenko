package ru.otus.testLauncher;

import java.lang.reflect.Method;

public class ResultRunTest {

    private final Method test;
    private final boolean flag;

    public ResultRunTest(Method test, boolean flag){
        this.test = test;
        this.flag = flag;
    }

    public String getMethodName (){
        return test.getName();
    }

    public boolean getFlag (){
        return flag;
    }
}
