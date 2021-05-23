package Optional;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) throws ClassNotFoundException, MalformedURLException {
        File myFolder = new File("C:\\Users\\Zetq\\IdeaProjects\\Advanced-Programming\\PaLab12\\out\\production\\PaLab12\\Optional");
        String classPackage = "Optional.";

        ClassLoader loader = new URLClassLoader(new URL[] { myFolder.toURI().toURL() });
        File[] files = myFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        ArrayList<Class> classes = new ArrayList<>();
        for (File file : files) {
            String className = classPackage + file.getName().substring(0, file.getName().length() - 6);
            Class<?> cls = loader.loadClass(className);
            classes.add(cls);
        }

        for (int i = 0; i < classes.size(); i++) {
            Class<?> cls = classes.get(i);

            System.out.println("Class Name: " + cls.getName());
            System.out.println("Package Name: " + cls.getPackage());
            Method[] methods = cls.getDeclaredMethods();
            System.out.println("Methods of class '" + cls.getSimpleName() + "' :");
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {

                        method.invoke(null);
                        System.out.println("Test on " + method + " succeeded.");
                    } catch (Throwable ex) {
                        System.out.println("Test on " + method + " failed: " + ex.getCause());
                    }
                }
            }
            System.out.println();
        }
    }
}
