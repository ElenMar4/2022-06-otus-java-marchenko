package ru.otus.testLauncher;

import ru.otus.testLauncher.Annotations.After;
import ru.otus.testLauncher.Annotations.Before;
import ru.otus.testLauncher.Annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestLauncher {

    private final Class<?> testClass;
    private Object object;

    public TestLauncher(String className) throws ClassNotFoundException {
        testClass = Class.forName(className);
    }

    private Constructor<?> getConstructor() throws NoSuchMethodException {
        return testClass.getConstructor();
    }

    public void  runTests () throws InvocationTargetException, IllegalAccessException {

        Method[] methods = testClass.getMethods();

        List<Method> methodsTest = findMethodsWithAnnotation(methods, Test.class);
        List<Method> methodsBefore = findMethodsWithAnnotation(methods, Before.class);
        List<Method> methodsAfter = findMethodsWithAnnotation(methods, After.class);

        List<ResultRunTest> results = new ArrayList<>();

        for (Method method : methodsTest) {

            try {
                object = getConstructor().newInstance();
                playMethodsSettingsTest(methodsBefore);
                results.add(playMethodWithTestAnnotation(method));
            } catch (Exception e) {
                results.add(new ResultRunTest(method,false));
            } finally {
                playMethodsSettingsTest(methodsAfter);
            }
        }
        new TestResult(testClass, results).printResultRunTest();
    }

    private ArrayList<Method> findMethodsWithAnnotation(Method[] methods, Class<? extends Annotation> annotation)  {

        ArrayList<Method> arrayMethods = new ArrayList<>();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(annotation)) {
                continue;
            }
            arrayMethods.add(method);
//            System.out.println("Method [" + method.getName() + "] marked [@" + annotation.getName() + "]");
        }
        return arrayMethods;
    }

    private void playMethodsSettingsTest(List<Method> methods) throws InvocationTargetException, IllegalAccessException {
        for(Method method : methods){
            playMethod(method);
        }
    }

    private void playMethod (Method method) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(object);
    }

    private ResultRunTest playMethodWithTestAnnotation(Method method){
        try {
            playMethod(method);
            return new ResultRunTest (method, true) {
            };
        } catch (IllegalAccessException | InvocationTargetException e) {
            return new ResultRunTest(method, false);
        }
    }
}

