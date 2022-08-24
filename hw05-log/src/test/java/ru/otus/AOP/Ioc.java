package ru.otus.AOP;

import ru.otus.TestCalculation.TestLogging;
import ru.otus.TestCalculation.TestLoggingInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;

public class Ioc {
    private Ioc() {
    }

    public static TestLoggingInterface createMyLogging(Class<TestLoggingInterface> clazz, TestLogging logging) {

        final List<String> logMethods = new LinkedList<>();
        Method[] methods1 = logging.getClass().getMethods();

        for (Method temp : methods1) {
            if (temp.isAnnotationPresent(Log.class)) {
                logMethods.add(temp.getName() + Arrays.stream(temp.getParameters()).toList());
            }
        }
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{clazz}, new DemoInvocationHandler(logging, logMethods));
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final TestLoggingInterface logging;
        private final List<String> methods;

        DemoInvocationHandler(TestLoggingInterface logging, List<String> methods) {
            this.logging = logging;
            this.methods = methods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            addStringToMethodWithAnnotationLog(method, args);
            return method.invoke(logging, args);
        }

        public void addStringToMethodWithAnnotationLog(Method method, Object[] args) {
            if (methods.contains(method.getName() + Arrays.stream(method.getParameters()).toList())) {
                if (args == null) {
                    System.out.println("executed method: " + method.getName() + ", no parameters");
                } else {
                    System.out.println("executed method: " + method.getName() + ", param: " + Arrays.stream(args)
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ")));
                }
            }
        }
    }
}

