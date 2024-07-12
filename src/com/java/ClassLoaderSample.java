package com.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * ClassLoader: It is a subsystem inside JVM that is responsible for loading class files over runtime to prepare for execution.
 * <p>
 * There are mainly three types of ClassLoaders:
 * <p>
 * Bootstrap ClassLoader: Loads the internal classes of JDK and core libraries. Loads classes located in rt.jar and jre/lib. It is also named as Primordial class loader.
 * Extension ClassLoader: Loads all classes outside of Java core, all in jre/lib/ext directory. It is a child component of the Bootstrap classloader.
 * example PlatformClassLoader: Loads platform classes that are not part of the core but are still provided by the JDK.
 * Application/ System Class Loader: Loads all classes in the application classpath. This is a child component of the extension class loader.
 * NOTE: Bootstrap classloader is the Parent of all classloaders
 * <p>
 * <p>
 * class loaders work in a Delegation Model where they recursively try to resolve class up the chain if found they return the class file else throw NoClassDefFoundError or ClassNotFoundException
 * <p>
 * When a request to load a class file is triggered to the System classloader, then System delegates it to extension classloader.
 * If the extension class loader can’t load class then the extension is delegated to Bootstrap.
 * If bootstrap also can’t load it then only the System classloader tries to load it.
 * <p>
 * This principle states that the child class loader can see all the classes loaded by the parent ClassLoader.
 * However, the parent class loader cannot see classes loaded by the child class loader.
 * Because delegate up the chain and resolve it while walking backward.
 */
public class ClassLoaderSample {
    public static void main(String[] args) {
        System.out.println("ClassLoader of this class : " + ClassLoaderSample.class.getClassLoader());
        System.out.println(
                "ClassLoader of DriverManager class : " + DriverManager.class.getClassLoader());
        System.out.println("ClassLoader of ArrayList class : " + ArrayList.class.getClassLoader()); // returns Null because Bootstrap classloader is written in native code, and cannot be represented as a Java class.

        // System.out.println(Class.forName("com.example.test.ABCApplication"));
        //  see the chain i.e BuiltInClassLoader -> AppClassLoader -> BootstrapClassLoader
        /**
         OUTPUT:
         Exception in thread "main" java.lang.ClassNotFoundException: com.example.test.ABCApplication
         at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
         at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
         at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520)
         at java.base/java.lang.Class.forName0(Native Method)
         at java.base/java.lang.Class.forName(Class.java:375)
         at com.pavan.concurrency.ClassLoaderTest.main(ClassLoaderTest.java:10)
         */

        String fullyQualifiedName = "com.java.ExecutorServiceExample";

        try {
            Class<?> clazz = Class.forName(fullyQualifiedName);
            System.out.println("Found class : " + clazz.getSimpleName());
            /**
             You can not invoke getClassLoader in a static context
             hence we use
             ---> ClassName.class.getClassLoader()
             else we would use this at the object level like following
             ---> this.getClass().getClassLoader()
             */
            clazz = ClassLoaderSample.class.getClassLoader().loadClass(fullyQualifiedName);
            System.out.println("Found class : " + clazz.getSimpleName());
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
    }
}


/**
 * These classes are more than sufficient for dynamic behavior if classes are in the file system.
 * However, in cases where a file can be in an object store/ a URL then JVM supports writing custom class loaders.
 */
class CustomClassLoader extends ClassLoader {

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}

/**
 * Practical Scenarios where Custom Class Loader will be required
 * <p>
 * 1. Dynamic Class Loading
 * In applications that need to load classes at runtime from different sources, such as network locations, databases, or encrypted files, a custom class loader can manage these dynamic loading requirements.
 * Example: An application server might need to load classes from deployed applications at runtime.
 * <p>
 * 2. Isolation
 * When different components of an application need to be isolated from each other, such as in plugin architectures or modular systems, custom class loaders can provide class isolation.
 * Example: A plugin system where each plugin is loaded with its own class loader to avoid conflicts between different versions of the same class.
 * <p>
 * 3. Bytecode Manipulation
 * Custom class loaders can be used to load classes with modified bytecode, enabling on-the-fly instrumentation, transformations, or enhancing classes with additional behavior.
 * Example: A monitoring tool that instruments classes to add logging or profiling capabilities.
 * <p>
 * 4. Sandboxing
 * Creating a controlled execution environment where certain classes or packages are restricted. This is particularly useful for running untrusted code safely.
 * Example: A web browser that runs applets in a sandbox, restricting access to certain classes.
 * <p>
 * 5. Custom Resource Management
 * Loading resources in a specialized manner, such as decrypting resources on the fly or loading resources from unconventional locations.
 * Example: An application that loads encrypted configuration files.
 */