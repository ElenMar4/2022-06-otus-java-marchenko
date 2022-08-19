package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ioc {
    private Ioc() {
    }

    static TestLoggingInterface createMyLogging(Class<TestLoggingInterface> clazz, TestLogging logging) {

        final List<Method> methods = Arrays.stream(logging.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(Log.class))
                .collect(Collectors.toList());

        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{clazz}, new DemoInvocationHandler(logging, methods));
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final TestLoggingInterface logging;
        private final List<Method> methods;

        DemoInvocationHandler(TestLoggingInterface logging, List<Method> methods) {
            this.logging = logging;
            this.methods = methods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            addStringToMethodWithAnnotationLog(method, args);
            return method.invoke(logging, args);
        }

        public void addStringToMethodWithAnnotationLog(Method method, Object[] args) {
            final String methodName = method.getName();
            final Class<?>[] parameterTypesMethod = method.getParameterTypes();
            for (Method current : methods) {
                if ((methodName.equals(current.getName()) && Arrays.equals(parameterTypesMethod, current.getParameterTypes()))) {
                    System.out.println("executed method: " + method.getName() + ", param: " + Arrays.stream(args)
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ")));
                }
            }
        }
    }
}

