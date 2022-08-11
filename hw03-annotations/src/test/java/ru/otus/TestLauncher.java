package ru.otus;

import ru.otus.Annotations.After;
import ru.otus.Annotations.Before;
import ru.otus.Annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestLauncher {

    public static void main(String[] args) throws Exception {

        TestResult result = new TestResult();

        Class<CalculatorTest> clazz = CalculatorTest.class;
        Method[] methods = clazz.getMethods();

        ArrayList<Method> methodTest = findMethodsWithAnnotation(methods, Test.class);
        ArrayList<Method> methodBefore = findMethodsWithAnnotation(methods, Before.class);
        ArrayList<Method> methodAfter = findMethodsWithAnnotation(methods, After.class);

        System.out.println();

        for (Method method : methodTest) {

            UnitTest test = new CalculatorTest();

            playMethodsWithBeforeOrAfterAnnotation(methodBefore.get(0), test);
            playMethodWithTestAnnotation(method, test, result);
            playMethodsWithBeforeOrAfterAnnotation(methodAfter.get(0), test);
        }

        System.out.println("\n" + result.printResultRunTest());
    }

    static void playMethodsWithBeforeOrAfterAnnotation(Method method, UnitTest test) throws InvocationTargetException, IllegalAccessException {
        assert method != null;
        method.invoke(test);
    }

    static void playMethodWithTestAnnotation(Method method, UnitTest test, TestResult result) throws InvocationTargetException, IllegalAccessException {
        assert method != null;
        if ((boolean) method.invoke(test)) {
            result.setSuccessfulTest(result.getSuccessfulTest() + 1);
            result.addResultRunMethods(method, true);
        } else {
            result.setFailedTest(result.getFailedTest() + 1);
            result.addResultRunMethods(method, false);
        }
        result.setAllRunTest(result.getAllRunTest() + 1);
    }

    static ArrayList<Method> findMethodsWithAnnotation(Method[] methods, Class<? extends Annotation> annotation)  {

        ArrayList<Method> arrayMethods = new ArrayList<>();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(annotation)) {
                continue;
            }
            arrayMethods.add(method);
            System.out.println("Method [" + method.getName() + "] marked [@" + annotation.getName() + "]");
        }
        return arrayMethods;
    }
}

