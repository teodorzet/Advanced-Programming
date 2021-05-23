package Compulsory;

import org.junit.Test;

import java.lang.reflect.Method;

public class Main {

    public static void main(String args[]) throws ClassNotFoundException {

        Class<?> cls = Class.forName("Compulsory.MyClass");

        System.out.println("Class Name: " + cls.getName());
        System.out.println("Package Name: " + cls.getPackage());
        Method[] methods = cls.getDeclaredMethods();
        System.out.println("Methods of class '" + cls.getSimpleName() + "' :");
        for (Method method : methods){
            System.out.println(method.getName());
        }

        for(Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                    System.out.println("Test on " + method + " succeeded.");
                } catch (Throwable ex) {
                    System.out.println("Test on " + method + " failed: " + ex.getCause());
                }
            }
        }
    }
}
